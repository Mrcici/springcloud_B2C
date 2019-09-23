package com.cici.entity.wallet.requestbody;

import com.cici.base.BaseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 15:27
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWalletDrawacashRequestBody extends BaseBody {

    private Long id;

    private Long WalletId;

    private Integer amount;

    private Integer amountLeft;
}
