package com.cici.service;

import com.cici.entity.account.Account;

/**
 * @author ：cici
 * @date ：Created in 2019/9/21 11:42
 */
public interface AccountService {

    Account findAccountByUsername(String username);

    Account showMe();
}
