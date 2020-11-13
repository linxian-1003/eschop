package com.neu.order_service.domain;

import com.neu.common.domain.BaseDomain;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Book extends BaseDomain {

    private Integer id;
    private Integer memberId;  //会员的id
    private String memberName; //会员名
    private String bookNum;
    private BigDecimal totalPrice;
    private Integer totalCount;
    private String payNum;
    private Integer payType;
    private Date payTime;
    private Date orderTime;
    private String address;
    private String phone;
    private Integer status;
    private List<BookDetail> bookDetailList;



}
