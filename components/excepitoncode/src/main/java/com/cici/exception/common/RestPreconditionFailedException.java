package com.cici.exception.common;


import com.cici.exception.ExceptionEnum;

import java.io.Serializable;

/**
 * 412
 */
public class RestPreconditionFailedException extends RuntimeException {

    public RestPreconditionFailedException(String message) {
        super(message);
    }

    public RestPreconditionFailedException(ExceptionEnum enumeration, Serializable message) {
        String m = enumeration.makeExceptionStr(message);
        throw new RestPreconditionFailedException(m);
    }

    public RestPreconditionFailedException(ExceptionEnum enumeration) {
        String m = enumeration.makeExceptionStr();
        throw new RestPreconditionFailedException(m);
    }
}
