package com.neu.member_service.domain;

import lombok.Data;

@Data
public class Member {
    private Integer id;
    private String name;
    private String loginName;
    private String password;
    private String payPassword;

    private String phone;
    private String headImg;
    private String address;

}
