package com.cici.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cici.entity.wallet.UserWallet;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 16:14
 */
public interface UserWalletMapper  extends BaseMapper<UserWallet> {

    int updateBalance(@Param("walletId") Long walletId,@Param("amount") Integer amount,@Param("currentTime") Date currentTime);

    void excute();
}
