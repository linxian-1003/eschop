package com.neu.product_service.service.impl;

import com.neu.product_service.dao.ProductTypeDao;
import com.neu.product_service.domain.ProductType;
import com.neu.product_service.query.ProductTypeQuery;
import com.neu.product_service.service.ProductTypeService;
import com.neu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, ProductTypeQuery> implements ProductTypeService {

    @Autowired
    public void setProductDao(ProductTypeDao productTypeDao){
        this.baseMapper = productTypeDao;
    }
}
