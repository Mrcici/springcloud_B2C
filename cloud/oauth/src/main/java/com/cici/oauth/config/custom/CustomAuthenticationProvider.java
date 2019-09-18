package com.cici.oauth.config.custom;

import com.cici.exception.common.ExceptionEnumImpl;
import com.cici.exception.common.RestPreconditionFailedException;
import com.cici.tools.StringEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 权限验证
 */
@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    @Qualifier("AccountDetailsService")
    @Lazy
    private UserDetailsService userDetailsService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Optional.ofNullable(authentication).orElseThrow(() -> new RestPreconditionFailedException(ExceptionEnumImpl.INVALID_TOKEN,
                "登录异常"));
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED"
                : authentication.getName();
        String password = (String) authentication.getCredentials();
        password = StringEncryptUtil.Encrypt(password,"MD5");
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new RestPreconditionFailedException(ExceptionEnumImpl.ACCOUNT_NOT_FOUND,
                    "手机号未注册,请注册");
        }
        //拿到当前登录的客户端信息
        Principal principal = Optional.ofNullable(request.getUserPrincipal())
                .orElseThrow(() -> new RestPreconditionFailedException(ExceptionEnumImpl.INVALID_TOKEN,
                        "登录异常"));

        //优先匹配密码
        if (password.equals(userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            throw new RestPreconditionFailedException(ExceptionEnumImpl.ACCOUNT_PASSWORD_ERROR,
                    "用户密码错误");
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
