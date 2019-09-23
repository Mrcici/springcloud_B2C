package com.cici.controller;

import com.cici.entity.wallet.UserWallet;
import com.cici.entity.wallet.requestbody.UserWalletCreateRequestBody;
import com.cici.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 16:13
 */
@RestController
@RequestMapping(value = "wallet")
public class UserWalletController {

    @Autowired
    private UserWalletService userWalletService;

    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public UserWallet createUserWallet(@RequestBody UserWalletCreateRequestBody body){
        return userWalletService.createUserWallet(body);
    }
}
