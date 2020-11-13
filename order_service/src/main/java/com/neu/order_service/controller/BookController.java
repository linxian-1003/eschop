package com.neu.order_service.controller;

import com.github.pagehelper.PageInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Http;
import com.neu.base.anno.ControllerAnno;
import com.neu.base.controller.BaseJsonController;
import com.neu.common.utils.JsonModel;
import com.neu.order_service.domain.Book;
import com.neu.order_service.domain.BookDetail;
import com.neu.order_service.query.BookQuery;
import com.neu.order_service.service.BookService;
import com.neu.product_service.domain.Product;
import com.neu.product_service.query.ProductQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("books")
public class BookController{

    @Autowired
    BookService bookService;

    @PostMapping
    @ResponseBody
    public JsonModel order(@RequestBody Book book){
        //System.out.println(0/id); //模拟熔断

        JsonModel jsonModel = new JsonModel();

        jsonModel.setSuccess(bookService.order(book)==1);
        jsonModel.setMsg(jsonModel.isSuccess()?"下单成功":"下单失败");
        return jsonModel;
    }

    @RequestMapping(value = "/query",method = RequestMethod.POST )
    @ResponseBody
    public JsonModel list(@RequestBody BookQuery bookQuery){
        JsonModel jsonModel = new JsonModel();
        PageInfo<Book> pageInfo = bookService.findByQuery(bookQuery);
        System.out.println(pageInfo);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("查找订单成功");
        jsonModel.setData(pageInfo.getList());
        jsonModel.setTotal(pageInfo.getTotal());
        return jsonModel;
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel edit(@RequestBody Book book){
        JsonModel jsonModel = new JsonModel();
        boolean sign = bookService.update(book);
        if(sign){
            jsonModel.setSuccess(true);
            jsonModel.setMsg("编辑成功");
        }else{
            jsonModel.setSuccess(false);
            jsonModel.setMsg("编辑失败");
        }
        return jsonModel;
    }


}
