package com.cici.exception.common;


import com.cici.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * 404
 */
public class RestNotFoundException extends RuntimeException {

    public RestNotFoundException(String message) {
        super(message);
    }

    public RestNotFoundException(ExceptionEnum enumeration, Serializable message) {
        String m = enumeration.makeExceptionStr(message);
        throw new RestNotFoundException(m);
    }

    public RestNotFoundException(ExceptionEnum enumeration) {
        String m = enumeration.makeExceptionStr();
        throw new RestNotFoundException(m);
    }
}
