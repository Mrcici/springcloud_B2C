package com.cici.controller;

import com.cici.api.v1.feign.account.OrderServerFeign;
import com.cici.entity.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：cici
 * @date ：Created in 2019/9/20 11:51
 */
@RestController
@RequestMapping(value = "test")
@Slf4j
public class TestController {

    @Autowired
    private OrderServerFeign orderServerFeign;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    @ResponseBody
    public Order createOrder(){
        log.info("====");
        return orderServerFeign.createOrder();
    }
}
