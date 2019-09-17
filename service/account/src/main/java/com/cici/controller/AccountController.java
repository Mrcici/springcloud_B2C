package com.cici.controller;

import com.cici.entity.account.Account;
import com.cici.entity.account.requestbody.AccountCreateRequestBody;
import com.cici.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Cici
 * @Date: 2019/9/17 14:35
 */
@Controller
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Account createAccount(@RequestBody AccountCreateRequestBody body){
        return accountService.createAccount(body);
    }


}
