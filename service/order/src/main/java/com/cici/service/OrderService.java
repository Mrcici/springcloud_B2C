package com.cici.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cici.entity.order.Order;
import com.cici.entity.order.requestbody.OrderCreateRequestBody;

/**
 * @author ：cici
 * @date ：Created in 2019/9/10 11:40
 */
public interface OrderService extends IService<Order> {

    Order createOrder();

    Order testCreateOrder(OrderCreateRequestBody order);
}
