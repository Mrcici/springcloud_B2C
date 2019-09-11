//package com.cici.controller;
//
//import com.cici.entity.order.Order;
//import com.cici.entity.order.requestbody.OrderCreateRequestBody;
//import com.cici.service.TestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author ：cici
// * @date ：Created in 2019/9/10 11:35
// */
//@RestController
//@RequestMapping(value = "/test")
//public class TestController {
//
//    @Autowired
//    private TestService testService;
//
//    @RequestMapping(value = "/create",method = RequestMethod.GET)
//    @ResponseBody
//    public Order createOrder(){
//        return testService.createOrder();
//    }
//
////    @RequestMapping(value = "/create",method = RequestMethod.POST)
////    @ResponseBody
////    public Order testCreateOrder(@RequestBody OrderCreateRequestBody body){
////        return testService.testCreateOrder(body);
////    }
//}
