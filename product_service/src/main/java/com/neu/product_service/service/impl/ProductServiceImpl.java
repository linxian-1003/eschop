package com.neu.product_service.service.impl;

import com.neu.product_service.dao.ProductDao;
import com.neu.product_service.domain.Product;
import com.neu.product_service.domain.ProductType;
import com.neu.product_service.query.ProductQuery;
import com.neu.product_service.service.ProductService;
import com.neu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductQuery> implements ProductService {

    @Autowired
    public void setProductDao(ProductDao productDao){
        this.baseMapper = productDao;
    }


}
