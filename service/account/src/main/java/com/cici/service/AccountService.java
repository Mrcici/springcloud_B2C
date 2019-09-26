package com.cici.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cici.entity.account.Account;
import com.cici.entity.account.requestbody.AccountCreateRequestBody;

/**
 * @Author: Cici
 * @Date: 2019/9/17 14:26
 */
public interface AccountService extends IService<Account> {

    Account createAccount(AccountCreateRequestBody body);

    Account showMe(String username);

    void test();
}
