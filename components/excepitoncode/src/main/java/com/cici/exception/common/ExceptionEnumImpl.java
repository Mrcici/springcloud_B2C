package com.cici.exception.common;

import com.cici.exception.ExceptionEnum;

/**
 * @author ：cici
 * @date ：Created in 2019/9/11 14:50
 */
public enum  ExceptionEnumImpl  implements ExceptionEnum {

    //认证方面的
    INVALID_TOKEN(1000,"登录失败"),
    ACCOUNT_NOT_FOUND(1001,"手机未注册"),
    ACCOUNT_PASSWORD_ERROR(1002,"密码错误"),
    CREATE_ERROR(100000, "创建错误");

    private int code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    ExceptionEnumImpl(int code) {
        this.code = code;
    }

    ExceptionEnumImpl(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
