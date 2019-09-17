package com.cici.oauth.config.custom;

import com.cici.exception.common.ExceptionEnumImpl;
import com.cici.exception.common.RestPreconditionFailedException;
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
    @Lazy
    private ClientDetailsService clientDetailsService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    private UserDetailsChecker preAuthenticationChecks = new CustomAuthenticationProvider.DefaultPreAuthenticationChecks();
//    private static final int ABBREVIATION_LENGTH = 6;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        Optional.ofNullable(authentication).orElseThrow(() -> new RestPreconditionFailedException(ExceptionEnumImpl.INVALID_TOKEN,
                "登录异常"));

        // Determine username
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED"
                : authentication.getName();

        String password = (String) authentication.getCredentials();

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

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(getClientId(principal));

        //检查客户端与用户信息是否吻合
        clientChecks(userDetails, clientDetails);

        //优先匹配密码
        if (passwordEncoder.matches(password, userDetails.getPassword())) {

            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            throw new RestPreconditionFailedException(ExceptionEnumImpl.ACCOUNT_PASSWORD_ERROR,
                    "用户密码错误");
        }

    }

    private String getClientId(Principal principal) {
        Authentication client = (Authentication) principal;
        if (!client.isAuthenticated()) {
            log.debug("client id 拿不到");
            throw new RestPreconditionFailedException(ExceptionEnumImpl.INVALID_TOKEN,
                    "登录异常");
        }
        String clientId = client.getName();
        if (client instanceof OAuth2Authentication) {
            // Might be a client and user combined authentication
            clientId = ((OAuth2Authentication) client).getOAuth2Request().getClientId();
        }
        return clientId;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * 检验客户端条件
     *
     * @param userDetails
     * @param clientDetails
     */
    public void clientChecks(UserDetails userDetails, ClientDetails clientDetails) {


        //判断用户是否拥有权限访问该客户端
        log.info("进入权限认证");

//        if (ClientIdType.AGENT_CLIENT.getId().equals(clientDetails.getClientId())) {
//            if (!isHasClientIdAuthorities(userDetails, clientDetails)) {
//                log.debug("------------------------用户信息：" + userDetails.toString());
//                log.debug("------------------------客户端信息：" + clientDetails.toString());
//                throw new RestForbiddenException(ExceptionEnumImpl.ROLE_ACCESS_FORBIDDEN);
//
//            }
//            preAuthenticationChecks.check(userDetails);
//
//        }
//
//        if (ClientIdType.ADMIN_CLIENT.getId().equals(clientDetails.getClientId())) {
//            if (!isHasClientIdAuthorities(userDetails, clientDetails)) {
//                log.debug("------------------------用户信息：" + userDetails.toString());
//                log.debug("------------------------客户端信息：" + clientDetails.toString());
//                throw new RestForbiddenException(ExceptionEnumImpl.ROLE_ACCESS_FORBIDDEN);
//
//            }
//            preAuthenticationChecks.check(userDetails);
//
//
//        }


    }

    /**
     * 检查用户是否有权限登录该客户端
     *
     * @param userDetails
     * @param clientDetails
     * @return
     */
    private boolean isHasClientIdAuthorities(UserDetails userDetails, ClientDetails clientDetails) {

        return clientDetails.getAuthorities().stream().anyMatch(grantedAuthority ->
                userDetails.getAuthorities().stream()
                        .anyMatch((Predicate<GrantedAuthority>) authority ->
                                authority.getAuthority().equals(grantedAuthority.getAuthority())
                        ));


    }

    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        @Override
        public void check(UserDetails user) {
            if (!user.isAccountNonLocked()) {
                log.debug("User account is locked");

//                throw new RestPreconditionFailedException(ExceptionEnumImpl.ACCOUNT_NON_LOCKED,
//                        "用户帐号已被锁定");
            }

            if (!user.isEnabled()) {
                log.debug("User account is disabled");
//                throw new RestPreconditionFailedException(ExceptionEnumImpl.ACCOUNT_NOT_ENABLED,
//                        "账号已停用");
            }

            if (!user.isAccountNonExpired()) {
                log.debug("User account is expired");
//                throw new RestPreconditionFailedException(ExceptionEnumImpl.ACCOUNT_EXPIRED,
//                        "账号已过期");
            }
        }
    }


}
