package com.cici.oauth.service;

import com.cici.oauth.domain.Account;
import com.cici.oauth.domain.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("AccountDetailsService")
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountMapper.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("手机号未注册,请注册");
        }
        return account;
    }
}
