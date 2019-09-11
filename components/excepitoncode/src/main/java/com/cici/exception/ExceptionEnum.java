package com.cici.exception;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public interface ExceptionEnum {

    Integer getCode();

    String getMessage();

    default String makeExceptionStr(Serializable message) {
        Map<String, Serializable> exceptionMap = new HashMap<String, Serializable>(16);
        exceptionMap.put("code", getCode());
        exceptionMap.put("message", message);
        return JSON.toJSONString(exceptionMap);
    }

    default String makeExceptionStr() {
        Map<String, Serializable> exceptionMap = new HashMap<String, Serializable>(16);
        exceptionMap.put("code", getCode());
        exceptionMap.put("message", getMessage());
        return JSON.toJSONString(exceptionMap);
    }
}
