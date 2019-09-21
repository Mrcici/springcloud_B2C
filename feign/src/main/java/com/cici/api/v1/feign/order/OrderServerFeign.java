package com.cici.api.v1.feign.order;

import com.cici.api.v1.feign.order.fallbackImpl.OrderServerCallback;
import com.cici.entity.order.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ：cici
 * @date ：Created in 2019/9/10 11:09
 * 这个注解的value是对应服务的配置文件里的application name
 */
@FeignClient(value = "order-service",fallback = OrderServerCallback.class)
public interface OrderServerFeign {

    @RequestMapping(path = "/test/create",method = RequestMethod.GET)
    Order createOrder();

}
