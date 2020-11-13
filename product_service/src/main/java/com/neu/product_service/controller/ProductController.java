package com.neu.product_service.controller;

import com.neu.base.anno.ControllerAnno;
import com.neu.base.controller.BaseJsonController;
import com.neu.product_service.domain.Product;
import com.neu.product_service.query.ProductQuery;
import com.neu.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("products")
@ControllerAnno(modelName = "product", msg = "商品")
public class ProductController extends BaseJsonController<Product, ProductQuery> {

    @Autowired
    public void setProductService(ProductService productService){
        this.baseService = productService;
    }

}
