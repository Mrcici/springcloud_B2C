package com.cici.api.v1.feign.account.fallbackImpl;

import com.cici.api.v1.feign.account.OrderServerFeign;
import com.cici.api.v1.feign.config.RestServiceUnavailableException;
import com.cici.entity.order.Order;
import org.springframework.stereotype.Component;

/**
 * @author ：cici
 * @date ：Created in 2019/9/10 11:19
 */
@Component
public class OrderServerCallback implements OrderServerFeign {
    @Override
    public Order createOrder() {
        throw new RestServiceUnavailableException("服务调用失败");
    }
}
