package com.neu.adv_service.domain;

import java.util.ArrayList;
import java.util.List;

public class Adv {

    private Integer id;
    private String url;
    private int clickCount;
    private boolean online;
    private byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
