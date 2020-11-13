package com.neu.common.domain;

public class BaseDomain {

    private static final long serialVersionUID = -600415752154494413L;

    public Integer id;         //数据库主键

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
