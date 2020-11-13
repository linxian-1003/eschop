package com.neu.order_service.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDetail {

    private Integer id;
    private Integer bookId;
    private Integer productId;   //商品id
    private String productName;
    private String productImgs;
    private BigDecimal productPrice; //商品单价
    private Integer productCount; //商品数量
    private BigDecimal itemPrice;    //item的总价
}
