package com.cici.oauth.service;

import com.cici.oauth.domain.Account;
import com.cici.oauth.domain.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("AccountDetailsService")
@Slf4j
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountMapper.findByUsername(username);
        log.info("account:" + account.toString());
        if (account == null) {
            throw new UsernameNotFoundException("手机号未注册,请注册");
        }
        return account;
    }
}
