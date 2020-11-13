package com.neu.mapper;

import com.neu.common.domain.BaseDomain;
import com.neu.query.BaseQuery;

import java.util.List;

public interface BaseMapper<T extends BaseDomain,E extends BaseQuery> {

	T findById(int id);

	List<T> findByQuery(E e);

	int add(T t);

	int deleteById(int id);

	int deleteByIds(int[] ids);

	int update(T t);
}