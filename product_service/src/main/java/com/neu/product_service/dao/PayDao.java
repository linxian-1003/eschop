package com.neu.product_service.dao;

import com.neu.mapper.BaseMapper;
import com.neu.product_service.domain.Brand;
import com.neu.product_service.domain.Pay;
import com.neu.product_service.query.BrandQuery;
import com.neu.product_service.query.PayQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayDao extends BaseMapper<Pay, PayQuery> {


}
