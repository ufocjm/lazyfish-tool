package com.baomidou.mybatisplus.core.conditions.query;

import fun.nibaba.lazyfish.mybatis.plus.core.interfaces.LazyCompare;

/**
 * compare adapter
 * @author chenjiamin
 * @date 2022-03-15 10:55:04
 */
public interface LazyCompareAdapter<Child extends LazyCompare<Child, TableModel, Value>, TableModel, Value> extends LazyCompare<Child, TableModel, Value> {

}
