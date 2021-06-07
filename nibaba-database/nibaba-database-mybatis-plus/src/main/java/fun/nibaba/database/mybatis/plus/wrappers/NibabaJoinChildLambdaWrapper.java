package fun.nibaba.database.mybatis.plus.wrappers;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import fun.nibaba.database.mybatis.plus.enums.JoinType;
import fun.nibaba.database.mybatis.plus.exceptions.NibabaMybatisPlusException;
import fun.nibaba.database.mybatis.plus.interfaces.NibabaJoinCompare;
import fun.nibaba.database.mybatis.plus.segments.ColumnSegment;
import fun.nibaba.database.mybatis.plus.segments.CompareFieldSegment;
import fun.nibaba.database.mybatis.plus.segments.WhereSegment;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * lamda方式 联表查 sql 构造器
 *
 * @author chenjiamin
 * @date 2021/6/1 6:20 下午
 */
public class NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass>
        extends AbstractNibabaWrapper<JoinTableClass, NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass>>
        implements NibabaJoinCompare<JoinTableClass, NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass>> {

    private final JoinType joinType;

    private final NibabaJoinLambdaWrapper<MainTableClass> parentWrapper;

    public NibabaJoinChildLambdaWrapper(JoinType joinType,
                                        NibabaJoinLambdaWrapper<MainTableClass> parentWrapper,
                                        Class<JoinTableClass> mainTableClass,
                                        String tableNameAlias,
                                        int tableSeq,
                                        Map<String, Object> paramNameValuePairs) {
        this(joinType,
                parentWrapper,
                mainTableClass,
                tableNameAlias,
                tableSeq,
                new AtomicInteger(0),
                new WhereSegment(),
                paramNameValuePairs,
                null);
    }

    public NibabaJoinChildLambdaWrapper(JoinType joinType,
                                        NibabaJoinLambdaWrapper<MainTableClass> parentWrapper,
                                        Class<JoinTableClass> mainTableClass,
                                        String tableNameAlias,
                                        int tableSeq,
                                        AtomicInteger paramNameSeq,
                                        WhereSegment whereSegment,
                                        Map<String, Object> paramNameValuePairs,
                                        Map<String, Map<String, ColumnCache>> classColumnMap) {
        super(mainTableClass, tableNameAlias, tableSeq, paramNameSeq, whereSegment, paramNameValuePairs, classColumnMap);
        if (joinType == null) {
            throw new RuntimeException("关联查询类型不能为空");
        }
        this.joinType = joinType;
        this.parentWrapper = parentWrapper;
    }

    public String getJoinType() {
        return joinType.getSqlKey();
    }

    @Override
    public String getSqlSelect() {
        return this.selectSegment.getSqlSegment();
    }

    @Override
    public NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> instance() {
        return new NibabaJoinChildLambdaWrapper<>(this.joinType,
                this.parentWrapper,
                this.entityClass,
                this.getTableNameAlias(),
                this.tableSeq,
                this.paramNameSeq,
                new WhereSegment(),
                this.paramNameValuePairs,
                this.classColumnMap);
    }

    @Override
    public String getWhereSegment() {
        return ON + SPACE + super.getWhereSegment();
    }

    public NibabaJoinLambdaWrapper<MainTableClass> done() {
        return parentWrapper;
    }

    @Override
    public <RightColumnModel> NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> joinEq(boolean condition, SFunction<JoinTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn, String tableNameAlias) {
        if (!condition) {
            return this;
        }
        tableNameAlias = this.searchTableNameAlias(rightColumn, tableNameAlias);
        ColumnCache leftColumnCache = super.getColumnCache(leftColumn);
        ColumnCache rightColumnCache = super.getColumnCache(rightColumn);
        super.addWhereSegment(new CompareFieldSegment(new ColumnSegment(this.getTableNameAlias(), leftColumnCache.getColumnSelect()), SqlKeyword.EQ, new ColumnSegment(tableNameAlias, rightColumnCache.getColumnSelect())));
        return this;
    }

    @Override
    public <RightColumnModel> NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> joinNe(boolean condition, SFunction<JoinTableClass, ?> leftColumn, SFunction<RightColumnModel, ?> rightColumn, String tableNameAlias) {
        if (!condition) {
            return this;
        }
        tableNameAlias = this.searchTableNameAlias(rightColumn, tableNameAlias);
        ColumnCache leftColumnCache = super.getColumnCache(leftColumn);
        ColumnCache rightColumnCache = super.getColumnCache(rightColumn);
        super.addWhereSegment(new CompareFieldSegment(new ColumnSegment(this.getTableNameAlias(), leftColumnCache.getColumnSelect()), SqlKeyword.NE, new ColumnSegment(tableNameAlias, rightColumnCache.getColumnSelect())));
        return this;
    }

    @Override
    public <GroupByModel> NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> groupBy(boolean condition, SFunction<GroupByModel, ?> column) {
        throw new NibabaMybatisPlusException("请使用父级sql包装器进行分组");
    }

    /**
     * 获取表别名
     *
     * @param rightColumn    验证右边列的列名
     * @param tableNameAlias 右边列的表别名
     * @return
     */
    @Override
    public String searchTableNameAlias(SFunction<?, ?> rightColumn, String tableNameAlias) {
        LambdaMeta meta = LambdaUtils.extract(rightColumn);

        //如果是主表数据则直接验证并且返回
        if (StrUtil.isNotBlank(tableNameAlias)) {
            if (this.parentWrapper.getTableNameAlias().equals(tableNameAlias) && this.parentWrapper.entityClass == meta.getInstantiatedClass()) {
                return this.parentWrapper.getTableNameAlias();
            }
        } else {
            if (this.parentWrapper.entityClass == meta.getInstantiatedClass()) {
                return this.parentWrapper.getTableNameAlias();
            }
        }

        //验证是不是子表数据
        List<NibabaJoinChildLambdaWrapper<?, MainTableClass>> joinSegmentList = this.parentWrapper.getJoinSegmentList();
        if (StrUtil.isNotBlank(tableNameAlias)) {
            for (NibabaJoinChildLambdaWrapper<?, MainTableClass> joinSegment : joinSegmentList) {
                if (joinSegment.getTableNameAlias().equals(tableNameAlias) && joinSegment.entityClass == meta.getInstantiatedClass()) {
                    return tableNameAlias;
                }
            }
            throw new RuntimeException("找不到别名为[" + tableNameAlias + "]的条件构造器");
        } else {
            for (NibabaJoinChildLambdaWrapper<?, MainTableClass> joinSegment : joinSegmentList) {
                if (meta.getInstantiatedClass() == joinSegment.entityClass) {
                    return joinSegment.getTableNameAlias();
                }
            }
        }
        throw new RuntimeException("不存在类型为[" + meta.getInstantiatedClass().getName() + "]的条件构造器");
    }

}
