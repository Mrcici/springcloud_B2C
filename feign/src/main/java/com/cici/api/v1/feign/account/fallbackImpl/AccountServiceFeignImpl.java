package com.cici.api.v1.feign.account.fallbackImpl;

import com.cici.api.v1.feign.account.AccountServiceFeign;
import com.cici.entity.account.Account;
import org.springframework.stereotype.Component;

/**
 * @author ：cici
 * @date ：Created in 2019/9/21 11:26
 */
@Component
public class AccountServiceFeignImpl implements AccountServiceFeign {
    @Override
    public Account showMe(String username) {
        return null;
    }

    @Override
    public Account findAccountByUsername() {
        return null;
    }
}
