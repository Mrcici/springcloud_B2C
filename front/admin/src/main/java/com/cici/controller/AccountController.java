package com.cici.controller;

import com.cici.entity.account.Account;
import com.cici.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：cici
 * @date ：Created in 2019/9/21 11:41
 */
@RestController
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/show")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account showMe(){
        return accountService.showMe();
    }

}
