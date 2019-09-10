package com.cici.exception.common;


import com.cici.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * 401
 */
public class RestUnauthorizedException extends RuntimeException {

    public RestUnauthorizedException(String message) {
        super(message);
    }

    public RestUnauthorizedException(ExceptionEnum enumeration, Serializable message) {
        String m = enumeration.makeExceptionStr(message);
        throw new RestUnauthorizedException(m);
    }

    public RestUnauthorizedException(ExceptionEnum enumeration) {
        String m = enumeration.makeExceptionStr();
        throw new RestUnauthorizedException(m);
    }
}
