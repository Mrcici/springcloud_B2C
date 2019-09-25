package com.cici.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cici.entity.wallet.UserWallet;
import com.cici.entity.wallet.requestbody.UserWalletCreateRequestBody;
import com.cici.exception.common.ExceptionEnumImpl;
import com.cici.exception.common.RestPreconditionFailedException;
import com.cici.mapper.UserWalletMapper;
import com.cici.service.UserWalletService;
import com.cici.tools.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 16:20
 */
@Service
@Slf4j
public class UserWalletServiceImpl extends ServiceImpl<UserWalletMapper,UserWallet> implements UserWalletService {


    @Override
    public UserWallet createUserWallet(UserWalletCreateRequestBody body) {
        UserWallet userWallet = new UserWallet();
        BeanUtils.copy(body,userWallet);
        if (baseMapper.insert(userWallet)<0){
            throw new RestPreconditionFailedException(ExceptionEnumImpl.CREATE_ERROR,
                    "create error.");
        }
        return baseMapper.selectById(userWallet.getId());
    }

    @Override
    public UserWallet selectByWalletId(Long id) {
        UserWallet userWallet = baseMapper.selectById(id);
        return userWallet;
    }
}
