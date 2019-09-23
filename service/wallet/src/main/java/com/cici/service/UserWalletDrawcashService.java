package com.cici.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cici.entity.wallet.UserWalletDrawcash;
import com.cici.entity.wallet.requestbody.UserWalletDrawacashRequestBody;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 16:29
 */
public interface UserWalletDrawcashService extends IService<UserWalletDrawcash> {
    UserWalletDrawcash createUserWalletDrawcashController(UserWalletDrawacashRequestBody body);
}
