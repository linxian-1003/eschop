package com.neu.service;
import com.github.pagehelper.PageInfo;
import com.neu.common.domain.BaseDomain;
import com.neu.query.BaseQuery;

import java.util.List;

public interface BaseService<T extends BaseDomain,E extends BaseQuery> {
 
	T findById(int id);

    PageInfo<T> findByQuery(E e);

    boolean add(T t);

    boolean deleteById(int id);

    boolean deleteByIds(int[] ids);

    boolean update(T t);

    boolean save(T t);
}