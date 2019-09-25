package com.cici.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cici.entity.order.Order;

import java.util.Date;

/**
 * @author ：cici
 * @date ：Created in 2019/9/11 11:20
 */
public interface OrderMapper extends BaseMapper<Order> {
    int updateOrderStatus(String orderId, String status, String admin, Date currentTime);
}
