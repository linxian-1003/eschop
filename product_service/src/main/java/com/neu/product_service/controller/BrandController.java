package com.neu.product_service.controller;

import com.neu.base.anno.ControllerAnno;
import com.neu.base.controller.BaseJsonController;
import com.neu.product_service.domain.Brand;
import com.neu.product_service.domain.Product;
import com.neu.product_service.query.BrandQuery;
import com.neu.product_service.query.ProductQuery;
import com.neu.product_service.service.BrandService;
import com.neu.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("brands")
@ControllerAnno(modelName = "brand", msg = "品牌")
public class BrandController extends BaseJsonController<Brand, BrandQuery> {

    @Autowired
    public void setBrandService(BrandService brandService){
        this.baseService = brandService;
    }

}
