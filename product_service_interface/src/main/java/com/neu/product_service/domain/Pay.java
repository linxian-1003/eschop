package com.neu.product_service.domain;

import com.neu.common.domain.BaseDomain;
import lombok.Data;

@Data
public class Pay extends BaseDomain {
    private Integer id;
    private String name;
    private String detail;
}
