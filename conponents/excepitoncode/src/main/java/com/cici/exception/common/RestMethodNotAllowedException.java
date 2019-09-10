package com.cici.exception.common;

import com.cici.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * 405
 */
public class RestMethodNotAllowedException extends RuntimeException {
    public RestMethodNotAllowedException(String message) {
        super(message);
    }

    public RestMethodNotAllowedException(ExceptionEnum enumeration, Serializable message) {
        String m = enumeration.makeExceptionStr(message);
        throw new RestMethodNotAllowedException(m);
    }

    public RestMethodNotAllowedException(ExceptionEnum enumeration) {
        String m = enumeration.makeExceptionStr();
        throw new RestMethodNotAllowedException(m);
    }
}
