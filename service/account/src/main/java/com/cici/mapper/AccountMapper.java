package com.cici.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cici.entity.account.Account;

/**
 * @Author: Cici
 * @Date: 2019/9/17 14:28
 */
public interface AccountMapper extends BaseMapper<Account> {

    void selectByName();

    void updateByName();

    void updateByCity();
}
