package com.cici.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author 权限拦截器配置
 */
@Component
public class HttpSecurityConfig implements IHttpSecurityConfig {
    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configure(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .antMatchers("/api/v1/validate_code/**").permitAll()
                ;


    }
}
