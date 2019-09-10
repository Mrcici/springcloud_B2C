package com.cici.entity.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：cici
 * @date ：Created in 2019/9/9 15:20
 */
@Data
public class Order {

    private Long id;

    private Long productId;

    private BigDecimal totalMoney;

    private Integer num;
}
