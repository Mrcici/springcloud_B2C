package com.cici.api.v1.feign.config;

import com.cici.exception.ExceptionEnum;
import com.netflix.hystrix.exception.HystrixBadRequestException;

import java.io.Serializable;

/**
 * 503
 */
public class RestServiceUnavailableException extends HystrixBadRequestException {


    public RestServiceUnavailableException(String message) {
        super(message);
    }

    public RestServiceUnavailableException(ExceptionEnum enumeration, Serializable message) {
        super(message.toString());
        String m = enumeration.makeExceptionStr(message);
        throw new RestServiceUnavailableException(m);
    }

    public RestServiceUnavailableException(ExceptionEnum enumeration) {
        super(enumeration.getMessage());
        String m = enumeration.makeExceptionStr();
        throw new RestServiceUnavailableException(m);
    }
}
