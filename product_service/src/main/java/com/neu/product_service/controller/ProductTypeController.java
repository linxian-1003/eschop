package com.neu.product_service.controller;


import com.neu.base.anno.ControllerAnno;
import com.neu.base.controller.BaseJsonController;
import com.neu.product_service.domain.Product;
import com.neu.product_service.domain.ProductType;
import com.neu.product_service.query.ProductQuery;
import com.neu.product_service.query.ProductTypeQuery;
import com.neu.product_service.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("productTypes")
@ControllerAnno(modelName = "productType", msg = "商品类别")
public class ProductTypeController extends BaseJsonController<ProductType, ProductTypeQuery> {

    @Autowired
    public void setProductTypeService(ProductTypeService productTypeService){
        this.baseService = productTypeService;
    }

}
