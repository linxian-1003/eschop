package com.neu.product_service.controller;

import com.neu.base.anno.ControllerAnno;
import com.neu.base.controller.BaseJsonController;
import com.neu.product_service.domain.Brand;
import com.neu.product_service.domain.Pay;
import com.neu.product_service.query.BrandQuery;
import com.neu.product_service.query.PayQuery;
import com.neu.product_service.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pays")
@ControllerAnno(modelName = "pay", msg = "支付类型")
public class PayController extends BaseJsonController<Pay, PayQuery> {

    @Autowired
    public void setPayService(PayService payService){
        this.baseService = payService;
    }

}
