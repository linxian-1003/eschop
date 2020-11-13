package com.neu.product_service.domain;

import com.neu.common.domain.BaseDomain;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Brand extends BaseDomain {
    private Integer id;
    private String name;
}
