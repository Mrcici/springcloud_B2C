package com.cici.controller;

import com.cici.entity.order.Order;
import com.cici.entity.order.requestbody.OrderCreateRequestBody;
import com.cici.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：cici
 * @date ：Created in 2019/9/10 11:35
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    @ResponseBody
    public Order createOrder(){
        return orderService.createOrder();
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Order testCreateOrder(@RequestBody OrderCreateRequestBody body){
        return orderService.testCreateOrder(body);
    }
}
