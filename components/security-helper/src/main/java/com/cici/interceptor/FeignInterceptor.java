package com.cici.interceptor;

import com.google.common.base.Strings;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignInterceptor implements RequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(FeignInterceptor.class);

    final String AUTH_HEADER = "Authorization";
    final String TOKEN_TYPE = "TOKEN_TYPE";
    final String TOKEN_TYPE_CICI = "CICI_JWT";

    @Override
    public void apply(RequestTemplate requestTemplate){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes!=null){
            HttpServletRequest request = attributes.getRequest();

            String token = request.getHeader(AUTH_HEADER);
            logger.debug("token:{}", token);

            if (!Strings.isNullOrEmpty(token)) {
                requestTemplate.header(AUTH_HEADER, token);
                requestTemplate.header(TOKEN_TYPE, TOKEN_TYPE_CICI);
            }
        }

    }
}
