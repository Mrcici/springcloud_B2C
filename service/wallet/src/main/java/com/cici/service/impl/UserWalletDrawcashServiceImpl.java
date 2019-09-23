package com.cici.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cici.entity.wallet.UserWalletDrawcash;
import com.cici.entity.wallet.requestbody.UserWalletDrawacashRequestBody;
import com.cici.exception.common.ExceptionEnumImpl;
import com.cici.exception.common.RestPreconditionFailedException;
import com.cici.mapper.UserWalletDrawcashMapper;
import com.cici.service.UserWalletDrawcashService;
import com.cici.tools.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ：cici
 * @date ：Created in 2019/9/23 16:30
 */
@Slf4j
@Service
public class UserWalletDrawcashServiceImpl extends ServiceImpl<UserWalletDrawcashMapper, UserWalletDrawcash> implements UserWalletDrawcashService {



    @Override
    public UserWalletDrawcash createUserWalletDrawcashController(UserWalletDrawacashRequestBody body) {
        UserWalletDrawcash userWalletDrawcash = new UserWalletDrawcash();
        BeanUtils.copy(body,userWalletDrawcash);
        if (baseMapper.insert(userWalletDrawcash)<0){
            throw new RestPreconditionFailedException(ExceptionEnumImpl.CREATE_ERROR,
                    "create error.");
        }
        return baseMapper.selectById(userWalletDrawcash.getId());
    }
}
