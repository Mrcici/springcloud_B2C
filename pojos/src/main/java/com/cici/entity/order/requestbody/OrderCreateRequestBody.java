package com.cici.entity.order.requestbody;

import com.cici.base.BaseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author ：cici
 * @date ：Created in 2019/9/11 13:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequestBody extends BaseBody {

    private Long id;

    private Long productId;

    private BigDecimal totalMoney;

    private Integer num;
}
