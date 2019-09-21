package com.cici.service.impl;

import com.cici.api.v1.feign.account.AccountServiceFeign;
import com.cici.entity.account.Account;
import com.cici.exception.common.ExceptionEnumImpl;
import com.cici.exception.common.RestForbiddenException;
import com.cici.service.AccountService;
import com.cici.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：cici
 * @date ：Created in 2019/9/21 11:42
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountServiceFeign accountServiceFeign;


    @Override
    public Account findAccountByUsername(String username) {
        return accountServiceFeign.findAccountByUsername();
    }

    @Override
    public Account showMe() {
        if (!SecurityUtils.isAuthenticated()) {
            throw new RestForbiddenException(ExceptionEnumImpl.INVALID_TOKEN, "请登录再试");
        }

        String username = SecurityUtils.getCurrentUserLogin();
        return accountServiceFeign.showMe(username);
    }
}
