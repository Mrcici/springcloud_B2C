package com.cici.exception.common;


import com.cici.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * 403
 */
//@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RestForbiddenException extends RuntimeException {
    public RestForbiddenException(String message) {
        super(message);
    }

    public RestForbiddenException(ExceptionEnum enumeration, Serializable message) {
        String m = enumeration.makeExceptionStr(message);
        throw new RestForbiddenException(m);
    }

    public RestForbiddenException(ExceptionEnum enumeration) {
        String m = enumeration.makeExceptionStr();
        throw new RestForbiddenException(m);
    }
}
