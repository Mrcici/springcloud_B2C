package com.cici.bean;

import java.lang.reflect.Field;

public interface BeanCopyOperator<T> {

    void copy(Field source, Field target, Object sourceObj, Object targetObj, BeanCopyOperator... operators);
    boolean isSupport(String field);
}
