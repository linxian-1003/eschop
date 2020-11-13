package com.neu.product_service.dao;

import com.neu.mapper.BaseMapper;
import com.neu.product_service.domain.ProductType;
import com.neu.product_service.query.ProductTypeQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductTypeDao extends BaseMapper<ProductType, ProductTypeQuery> {

    
}
