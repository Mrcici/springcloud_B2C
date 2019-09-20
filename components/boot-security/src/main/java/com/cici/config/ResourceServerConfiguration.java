package com.cici.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;


@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final String jwtKey;
    private final String resourceId;
    @Autowired
    private  IHttpSecurityConfig httpSecurityConfig;

    public ResourceServerConfiguration(
            @Value("${custom.security.jwtKey}") String jwtKey,
            @Value("${custom.security.resourceId}") String resourceId) {
        this.jwtKey = jwtKey;
        this.resourceId = resourceId;
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(null).authenticationEntryPoint(customOAuth2AuthenticationEntryPoint());
        resources.tokenServices(defaultTokenServices());

    }

    @Bean
    AuthenticationEntryPoint customOAuth2AuthenticationEntryPoint() {
        return new CustomOAuth2AuthenticationEntryPoint();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        httpSecurityConfig.configure(http)
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/hystrix.stream/**", "/info", "/error").permitAll()
                .antMatchers("/v2/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().and()
                //不需要session来控制,所以这里可以去掉
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //退出登录自己来控制
//                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .logout().disable()
                //因为没用到cookies,所以关闭cookies
                .httpBasic().and().csrf().disable();
        // 除上面外的所有请求全部需要鉴权认证
        http.headers().cacheControl();
        // @formatter:on
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable();
    }


    /**
     * token存储,这里使用jwt方式存储
     *
     * @param
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        TokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
        return tokenStore;
    }

    /**
     * token converter
     * jwt公钥
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(jwtKey);
        return accessTokenConverter;
    }
//	@Bean
//	protected JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
//		Resource resource = new ClassPathResource("public.cert");
//		String publicKey = null;
//		try {
//			publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		converter.setVerifierKey(publicKey);
//		return converter;
//	}

    /**
     * 创建一个默认的资源服务token
     *
     * @return
     */
    @Bean
    public ResourceServerTokenServices defaultTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

}
