package com.cici.entity.wallet;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cici.base.DelFlagEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 15:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_wallet_drawcash")
public class UserWalletDrawcash extends DelFlagEntity {

    @TableId(type = IdType.ID_WORKER)
    private Long id;

    @TableField(value = "wallet_id")
    private Long walletId;

    @TableField(value = "amount")
    private Integer amount;

    @TableField(value = "amount_left")
    private Integer amountLeft;


}
