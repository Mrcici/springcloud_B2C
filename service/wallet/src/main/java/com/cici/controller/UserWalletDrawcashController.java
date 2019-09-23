package com.cici.controller;

import com.cici.entity.wallet.UserWalletDrawcash;
import com.cici.entity.wallet.requestbody.UserWalletDrawacashRequestBody;
import com.cici.service.UserWalletDrawcashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 16:27
 */
@RestController
@RequestMapping(value = "wallet/drawacash")
public class UserWalletDrawcashController {

    @Autowired
    private UserWalletDrawcashService userWalletDrawcashService;

    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public UserWalletDrawcash createUserWalletDrawcashController(@RequestBody UserWalletDrawacashRequestBody body){
        return userWalletDrawcashService.createUserWalletDrawcashController(body);
    }
}