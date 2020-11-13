package com.neu.product_service.dao;

import com.neu.mapper.BaseMapper;
import com.neu.product_service.domain.Product;
import com.neu.product_service.domain.ProductType;
import com.neu.product_service.query.ProductQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao extends BaseMapper<Product, ProductQuery> {


}
