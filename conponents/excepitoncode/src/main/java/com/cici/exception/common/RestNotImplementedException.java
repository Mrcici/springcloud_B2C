package com.cici.exception.common;


import com.cici.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * 501
 */
public class RestNotImplementedException extends RuntimeException {

    public RestNotImplementedException(String message) {
        super(message);
    }

    public RestNotImplementedException(ExceptionEnum enumeration, Serializable message) {
        String m = enumeration.makeExceptionStr(message);
        throw new RestNotImplementedException(m);
    }

    public RestNotImplementedException(ExceptionEnum enumeration) {
        String m = enumeration.makeExceptionStr();
        throw new RestNotImplementedException(m);
    }

}
