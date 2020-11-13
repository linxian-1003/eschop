package com.neu.order_service.exception;

public interface AbstractServiceException {
    Integer getCode();

    String getMessage();
}
