package com.cici.exception.common;

import com.cici.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * 500
 */
public class RestInternalServerErrorException extends RuntimeException {
    public RestInternalServerErrorException(String message) {
        super(message);
    }

    public RestInternalServerErrorException(ExceptionEnum enumeration, Serializable message) {
        String m = enumeration.makeExceptionStr(message);
        throw new RestInternalServerErrorException(m);
    }

    public RestInternalServerErrorException(ExceptionEnum enumeration) {
        String m = enumeration.makeExceptionStr();
        throw new RestInternalServerErrorException(m);
    }
}
