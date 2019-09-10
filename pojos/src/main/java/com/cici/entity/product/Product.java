package com.cici.entity.product;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：cici
 * @date ：Created in 2019/9/9 15:25
 */
@Data
public class Product {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer inventory;
}
