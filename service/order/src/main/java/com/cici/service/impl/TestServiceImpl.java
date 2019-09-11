//package com.cici.service.impl;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.cici.entity.order.Order;
//import com.cici.entity.order.requestbody.OrderCreateRequestBody;
//import com.cici.exception.common.ExceptionEnumImpl;
//import com.cici.exception.common.RestPreconditionFailedException;
//import com.cici.mapper.TestMapper;
//import com.cici.service.TestService;
//import com.cici.tools.BeanUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
///**
// * @author ：cici
// * @date ：Created in 2019/9/10 11:41
// */
//@Service
//@Slf4j
//public class TestServiceImpl extends ServiceImpl<TestMapper,Order> implements TestService {
//
//    @Override
//    public Order createOrder() {
//        Order order = new Order();
//        order.setId(11111L);
//        order.setNum(1);
//        order.setProductId(1234123L);
//        order.setTotalMoney(BigDecimal.valueOf(100L));
//        log.info("======");
//        return order;
//    }
//
//    @Override
//    public Order testCreateOrder(OrderCreateRequestBody body) {
//        Order order = new Order();
//        BeanUtils.copy(body,order);
//        if(baseMapper.insert(order)<0){
//            throw new RestPreconditionFailedException(ExceptionEnumImpl.CREATE_ERROR,
//                    "create error.");
//        }
//        return baseMapper.selectById(order.getId());
//    }
//
//}
