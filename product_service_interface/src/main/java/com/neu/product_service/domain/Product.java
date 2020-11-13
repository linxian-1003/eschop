package com.neu.product_service.domain;

import com.neu.common.domain.BaseDomain;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product extends BaseDomain {

    private Integer id;
    private String name;

    private BigDecimal oriPrice;  //单位分
    private BigDecimal price;    //单位分
    private String imgs;

    private String packageInfo;

    private String packages;

    private String detail;

    private Integer brandId;
    private String brandName;

    private Integer productTypeId;

    private Integer stock;

}
