package com.neu.order_service.exception;

import lombok.Data;

public enum ExceptionEnum  implements AbstractServiceException {

    PARAM_IS_NULL(7420000, "参数为空"),
            ;
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    ExceptionEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}