package com.cici.exception.common;


import com.cici.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * 400
 */
public class RestBadRequestException extends RuntimeException {
    public RestBadRequestException(String message) {
        super(message);
    }

    public RestBadRequestException(ExceptionEnum enumeration, Serializable message) {
        String m = enumeration.makeExceptionStr(message);
        throw new RestBadRequestException(m);
    }

    public RestBadRequestException(ExceptionEnum enumeration) {
        String m = enumeration.makeExceptionStr();
        throw new RestBadRequestException(m);
    }
}
