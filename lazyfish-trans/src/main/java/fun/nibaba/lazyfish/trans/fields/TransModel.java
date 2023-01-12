package fun.nibaba.lazyfish.trans.fields;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;
import fun.nibaba.lazyfish.trans.helpers.TransMethod;
import fun.nibaba.lazyfish.trans.helpers.TransMethodHelper;
import fun.nibaba.lazyfish.trans.helpers.TransModelCache;
import fun.nibaba.lazyfish.trans.helpers.TypeHelper;
import fun.nibaba.lazyfish.trans.processors.TransScanProcessor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 翻译对象
 */
@Getter
public class TransModel implements ITransElement {

    /**
     * 类型
     */
    private final Class<?> classType;

    /**
     * 需要翻译的字段
     */
    private final List<ITransField> transFields;

    /**
     * 是否在扫描中<br/>
     * 为了避免第一个field就是当前类型或者泛型为当前类型造成的死循环
     */
    private boolean scanning = false;

    public TransModel(Class<?> classType) {
        this.transFields = Lists.newArrayList();
        this.classType = classType;
        // 缓存自己
        TransModelCache.put(classType, this);
    }

    @Override
    public void scan(List<TransScanProcessor<?>> scanProcessors) {
        if (this.scanning) {
            return;
        } else {
            this.scanning = true;
        }
        List<ITransField> javaTypeTransFields = Lists.newArrayList();
        Field[] fields = ReflectUtil.getFields(this.classType);
        // 先处理基本类型
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (TypeHelper.isJavaClassNotColl(field.getType())) {
                TransField transField = new TransField(field);
                transField.scan(scanProcessors);
                javaTypeTransFields.add(transField);
            }
        }

        // 处理集合数据结构中的泛型
        List<TransListField> collectionTypeTransFields = Lists.newArrayList();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (TypeHelper.isCollection(field.getType())) {
                TransListField transListField = new TransListField(field, new TransMethod(field, TransMethodHelper.generalGetter(field), null));
                transListField.scan(scanProcessors);
                collectionTypeTransFields.add(transListField);
            }
        }

        // 处理泛型是基本类型的
//        TransListPrimitiveField
        List<TransListPrimitiveField> collectionPrimitiveTransFields = Lists.newArrayList();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (TypeHelper.isCollection(field.getType())) {
                TransListPrimitiveField transListField = new TransListPrimitiveField(field, new TransMethod(field, TransMethodHelper.generalGetter(field), null));
                transListField.scan(scanProcessors);
                collectionPrimitiveTransFields.add(transListField);
            }
        }

        // 处理用户自己创建的对象
        List<TransObjectField> objectTypeTransFields = Lists.newArrayList();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (TypeHelper.isNotJavaClass(field.getType())) {
                TransObjectField transListField = new TransObjectField(field, new TransMethod(field, TransMethodHelper.generalGetter(field), null));
                transListField.scan(scanProcessors);
                objectTypeTransFields.add(transListField);
            }
        }

        // 统一验证是否有效
        this.transFields.addAll(javaTypeTransFields.stream().filter(ITransElement::valid).collect(Collectors.toList()));
        this.transFields.addAll(collectionTypeTransFields.stream().filter(ITransElement::valid).collect(Collectors.toList()));
        this.transFields.addAll(collectionPrimitiveTransFields.stream().filter(ITransElement::valid).collect(Collectors.toList()));
        this.transFields.addAll(objectTypeTransFields.stream().filter(ITransElement::valid).collect(Collectors.toList()));
    }

    @Override
    public void buildHandles(List<ITransHandle> transHandles, Class<?> classType, Object returnValue) {
        if (TypeHelper.isList(returnValue.getClass())) {
            List<Object> returnValues = (List<Object>) returnValue;
            for (Object value : returnValues) {
                this.buildHandles(transHandles, classType, value);
            }
        } else {
            for (ITransField transField : this.transFields) {
                transField.buildHandles(transHandles, classType, returnValue);
            }
        }
    }

    @Override
    public boolean valid() {
        return CollUtil.isNotEmpty(this.transFields);
    }

}
