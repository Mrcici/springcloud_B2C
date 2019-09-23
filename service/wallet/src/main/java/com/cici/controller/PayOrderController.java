package com.cici.controller;

import com.cici.entity.wallet.PayOrder;
import com.cici.entity.wallet.requestbody.PayOrderCreateRequestBody;
import com.cici.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 17:42
 */
@RestController
@RequestMapping(value = "/pay")
public class PayOrderController {

    @Autowired
    private PayOrderService payOrderService;

    @RequestMapping(value = "create")
    @ResponseBody
    public PayOrder payApi(@RequestBody PayOrderCreateRequestBody body){
        return payOrderService.payApi(body);
    }
}
