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
 * @date ：Created in 2019/9/9 15:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_wallet")
public class UserWallet extends DelFlagEntity {

    @TableId(type = IdType.ID_WORKER)
    private Long id;

    @TableField(value = "account_id")
    private Long accountId;

    @TableField(value = "amount")
    private Long amount;

}
