package com.neu.product_service.query;

import com.neu.query.BaseQuery;
import lombok.Data;

@Data
public class ProductQuery extends BaseQuery {

    private Integer productTypeId;
    private Integer id;
    private  String name;

}
