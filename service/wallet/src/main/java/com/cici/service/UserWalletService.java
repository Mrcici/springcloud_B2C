package com.cici.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cici.entity.wallet.UserWallet;
import com.cici.entity.wallet.requestbody.UserWalletCreateRequestBody;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 16:13
 */
public interface UserWalletService extends IService<UserWallet> {

    UserWallet createUserWallet(UserWalletCreateRequestBody body);

}
