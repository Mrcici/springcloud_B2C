package com.cici.entity.wallet.requestbody;

import com.cici.base.BaseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 15:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWalletCreateRequestBody extends BaseBody {

    private Long id;

    private Long accountId;

    private Long amount;
}
