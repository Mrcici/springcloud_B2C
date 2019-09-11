package com.cici.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cici.base.DelFlagEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author ：cici
 * @date ：Created in 2019/9/9 15:20
 * @Data 这个是lombok的一个注解，作用是自动生成get set方法
 * @Builder 这个构建对象的时候可以链式设值
 * @NoArgsConstructor 生成无参构造函数
 * @AllArgsConstructor 生成全参构造函数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "order")
public class Order extends DelFlagEntity {

    @TableId(type = IdType.ID_WORKER)
    private Long id;

    @TableField(value = "product_id")
    private Long productId;

    @TableField(value = "total_money")
    private BigDecimal totalMoney;

    @TableField(value = "num")
    private Integer num;
}
