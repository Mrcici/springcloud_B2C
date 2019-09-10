package com.cici.service.impl;

import com.cici.entity.order.Order;
import com.cici.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author ：cici
 * @date ：Created in 2019/9/10 11:41
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Override
    public Order createOrder() {
        Order order = new Order();
        order.setId(11111L);
        order.setNum(1);
        order.setProductId(1234123L);
        order.setTotalMoney(BigDecimal.valueOf(100L));
        log.info("======");
        return order;
    }

}
