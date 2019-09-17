package com.cici.oauth.domain;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：cici
 * @date ：Created in 2019/9/16 16:19
 */
public interface AccountMapper extends BaseMapper<Account> {

    Account findByUsername(@Param("username") String username);

}
