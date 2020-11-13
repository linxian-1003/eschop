package com.neu.order_service.exception;

import lombok.Data;

@Data
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public static final String REQUIRED_PARAM_SUFFIX = "000";
    public static final String ILLEGAL_PARAM_SUFFIX = "001";
    private Integer code;
    private String errorMessage;

    public CommonException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public CommonException(AbstractServiceException exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

}