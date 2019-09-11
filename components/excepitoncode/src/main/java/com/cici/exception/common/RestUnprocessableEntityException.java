package com.cici.exception.common;

import com.cici.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * 422
 */
public class RestUnprocessableEntityException extends RuntimeException {
    public RestUnprocessableEntityException(String message) {
        super(message);
    }

    public RestUnprocessableEntityException(ExceptionEnum enumeration, Serializable message) {
        String m = enumeration.makeExceptionStr(message);
        throw new RestUnauthorizedException(m);
    }

    public RestUnprocessableEntityException(ExceptionEnum enumeration) {
        String m = enumeration.makeExceptionStr();
        throw new RestUnprocessableEntityException(m);
    }
}
