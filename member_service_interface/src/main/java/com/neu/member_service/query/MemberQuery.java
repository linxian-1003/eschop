package com.neu.member_service.query;

import com.neu.query.BaseQuery;
import lombok.Data;

@Data
public class MemberQuery  extends BaseQuery {

    private String name;
    private String loginName;
    private String phone;
    private String address;
}