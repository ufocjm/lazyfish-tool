package fun.nibaba.database.mybatis.plus.wrappers;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import fun.nibaba.database.mybatis.plus.enums.JoinType;
import fun.nibaba.database.mybatis.plus.interfaces.NibabaOrder;
import fun.nibaba.database.mybatis.plus.segments.OrderBySegment;
import fun.nibaba.database.mybatis.plus.segments.WhereSegment;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * lamda 方式 sql构造器
 *
 * @author chenjiamin
 * @date 2021/6/1 5:23 下午
 */
public class NibabaJoinLambdaWrapper<MainTableClass> extends AbstractNibabaWrapper<MainTableClass, NibabaJoinLambdaWrapper<MainTableClass>>
        implements NibabaOrder<NibabaJoinLambdaWrapper<MainTableClass>> {

    /**
     * 排序sql 片段
     */
    protected final OrderBySegment orderSegment;

    /**
     * 关联sql片段
     */
    private List<NibabaJoinChildLambdaWrapper<?, MainTableClass>> joinSegmentList;

    public NibabaJoinLambdaWrapper(Class<MainTableClass> mainTableClass) {
        this(mainTableClass, null);
    }

    public NibabaJoinLambdaWrapper(Class<MainTableClass> mainTableClass, String tableNameAlias) {
//        super(mainTableClass, tableNameAlias, 0);
        this(mainTableClass,
                tableNameAlias,
                0,
                new AtomicInteger(0),
                new WhereSegment(),
                Maps.newHashMap(),
                new OrderBySegment(),
                null);
    }

    public NibabaJoinLambdaWrapper(Class<MainTableClass> mainTableClass,
                                   String tableNameAlias,
                                   int tableSeq,
                                   AtomicInteger paramNameSeq,
                                   WhereSegment whereSegment,
                                   Map<String, Object> paramNameValuePairs,
                                   OrderBySegment orderSegment,
                                   Map<String, Map<String, ColumnCache>> classColumnMap) {
        super(mainTableClass, tableNameAlias, tableSeq, paramNameSeq, whereSegment, paramNameValuePairs, classColumnMap);
        this.orderSegment = orderSegment != null ? orderSegment : new OrderBySegment();
    }

    @Override
    public String getSqlSelect() {
        if (selectSegment.isEmpty() && CollUtil.isEmpty(joinSegmentList)) {
            return null;
        }
        List<String> selectSegments = Lists.newArrayList();
        if (!selectSegment.isEmpty()) {
            selectSegments.add(selectSegment.getSqlSegment());
        }

        if (CollUtil.isNotEmpty(joinSegmentList)) {
            List<String> segmentList = joinSegmentList.stream().map(NibabaJoinChildLambdaWrapper::getSqlSelect).filter(StrUtil::isNotBlank).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(segmentList)) {
                selectSegments.add(String.join(COMMA, selectSegments));
            }
        }
        if (CollUtil.isEmpty(selectSegments)) {
            return null;
        }
        return String.join(COMMA, selectSegments);
    }

    @Override
    public NibabaJoinLambdaWrapper<MainTableClass> instance() {
        return new NibabaJoinLambdaWrapper<>(this.entityClass,
                this.getTableNameAlias(),
                this.tableSeq,
                this.paramNameSeq,
                new WhereSegment(),
                this.paramNameValuePairs,
                this.orderSegment,
                this.classColumnMap);
    }

    @Override
    public String getWhereSegment() {
        return super.getWhereSegment();
    }

    public List<NibabaJoinChildLambdaWrapper<?, MainTableClass>> getJoinSegmentList() {
        return this.joinSegmentList;
    }

    /**
     * inner join
     *
     * @param joinTableClass   关联的表名
     * @param <JoinTableClass> 关联的表类型
     * @return
     */
    public <JoinTableClass> NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> innerJoin(Class<JoinTableClass> joinTableClass) {
        return this.innerJoin(joinTableClass, null);
    }

    /**
     * inner join
     *
     * @param joinTableClass   关联的表名
     * @param tableNameAlias   关联的表的别名
     * @param <JoinTableClass> 关联的表类型
     * @return
     */
    public <JoinTableClass> NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> innerJoin(Class<JoinTableClass> joinTableClass, String tableNameAlias) {
        return this.join(joinTableClass, tableNameAlias, JoinType.INNER_JOIN);
    }

    /**
     * left join
     *
     * @param joinTableClass   关联的表名
     * @param <JoinTableClass> 关联的表类型
     * @return
     */
    public <JoinTableClass> NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> leftJoin(Class<JoinTableClass> joinTableClass) {
        return this.leftJoin(joinTableClass, null);
    }

    /**
     * left join
     *
     * @param joinTableClass   关联的表名
     * @param tableNameAlias   关联的表的别名
     * @param <JoinTableClass> 关联的表类型
     * @return
     */
    public <JoinTableClass> NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> leftJoin(Class<JoinTableClass> joinTableClass, String tableNameAlias) {
        return this.join(joinTableClass, tableNameAlias, JoinType.LEFT_JOIN);
    }

    /**
     * join 查询
     *
     * @param joinTableClass   关联的表名
     * @param tableNameAlias   关联的表的别名
     * @param joinType         关联类型
     * @param <JoinTableClass> 关联的表类型
     * @return
     */
    private <JoinTableClass> NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> join(Class<JoinTableClass> joinTableClass, String tableNameAlias, JoinType joinType) {
        if (StrUtil.isBlank(tableNameAlias)) {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(joinTableClass);
            tableNameAlias = tableInfo.getTableName();
        }
        if (this.joinSegmentList == null) {
            this.joinSegmentList = Lists.newArrayList();
        }
        NibabaJoinChildLambdaWrapper<JoinTableClass, MainTableClass> joinWrapper = new NibabaJoinChildLambdaWrapper<>(joinType,
                this,
                joinTableClass, tableNameAlias,
                joinSegmentList.size() + 1,
                this.paramNameValuePairs,
                new AtomicInteger(0),
                new WhereSegment(),
                this.classColumnMap);

        this.joinSegmentList.add(joinWrapper);
        return joinWrapper;
    }

    @Override
    public <OrderByModel> NibabaJoinLambdaWrapper<MainTableClass> orderBy(SFunction<OrderByModel, ?> column, SqlKeyword orderRule) {
        return null;
    }

}
