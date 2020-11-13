package com.neu.product_service.service.impl;
import com.neu.product_service.dao.BrandDao;
import com.neu.product_service.domain.Brand;
import com.neu.product_service.query.BrandQuery;
import com.neu.product_service.service.BrandService;
import com.neu.product_service.service.ProductService;
import com.neu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends BaseServiceImpl<Brand, BrandQuery> implements BrandService {

    @Autowired
    public void setBrandDao(BrandDao brandDao){
        this.baseMapper = brandDao;
    }


}
