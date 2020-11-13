package com.neu.order_service.controller;

import com.github.pagehelper.PageInfo;
import com.neu.common.utils.JsonModel;
import com.neu.order_service.domain.Book;
import com.neu.order_service.domain.BookDetail;
import com.neu.order_service.query.BookQuery;
import com.neu.order_service.service.BookDetailService;
import com.neu.order_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("bookDetail")
public class BookDetailController {

    @Autowired
    BookDetailService bookDetailService;

    @PostMapping
    @ResponseBody
    public JsonModel add(@RequestBody BookDetail bookDetail){
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(bookDetailService.add(bookDetail)==1);
        jsonModel.setMsg(jsonModel.isSuccess()?"下单成功":"下单失败");
        return jsonModel;
    }



}
