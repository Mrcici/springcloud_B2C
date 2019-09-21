package com.cici.api.v1.feign.account;

import com.cici.api.v1.feign.account.fallbackImpl.AccountServiceFeignImpl;
import com.cici.entity.account.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ：cici
 * @date ：Created in 2019/9/21 11:24
 */
@FeignClient(value = "account-service",fallback = AccountServiceFeignImpl.class)
public interface AccountServiceFeign {

    @RequestMapping(method = RequestMethod.GET, path = "/accounts/principal")
    Account showMe(@RequestParam(value = "username") String username);

    @RequestMapping(method = RequestMethod.GET, path = "/api/v1/accounts/principal")
    Account findAccountByUsername();
}
