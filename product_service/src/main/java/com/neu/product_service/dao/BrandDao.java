package com.neu.product_service.dao;

import com.neu.mapper.BaseMapper;
import com.neu.product_service.domain.Brand;
import com.neu.product_service.domain.Product;
import com.neu.product_service.query.BrandQuery;
import com.neu.product_service.query.ProductQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandDao extends BaseMapper<Brand, BrandQuery> {


}
