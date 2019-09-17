package com.cici.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cici.entity.account.Account;
import com.cici.entity.account.requestbody.AccountCreateRequestBody;
import com.cici.exception.common.ExceptionEnumImpl;
import com.cici.exception.common.RestPreconditionFailedException;
import com.cici.mapper.AccountMapper;
import com.cici.service.AccountService;
import com.cici.tools.BeanUtils;
import com.cici.tools.StringEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Cici
 * @Date: 2019/9/17 14:26
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {


    @Override
    public Account createAccount(AccountCreateRequestBody body) {
        Account account = new Account();
        BeanUtils.copy(body,account);
        String pass = StringEncryptUtil.Encrypt(body.getPassword(),"MD5");
        account.setPassword(pass);
        if (baseMapper.insert(account)<0){
            throw new RestPreconditionFailedException(ExceptionEnumImpl.CREATE_ERROR,
                    "create error.");
        }
        return baseMapper.selectById(account.getId());
    }


}
