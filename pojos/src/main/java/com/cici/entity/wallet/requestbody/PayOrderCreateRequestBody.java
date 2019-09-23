package com.cici.entity.wallet.requestbody;

import com.cici.base.BaseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 17:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderCreateRequestBody extends BaseBody {

    private Long id;

    private Long walletId;

    private Integer amount;

    private Long orderId;
}
