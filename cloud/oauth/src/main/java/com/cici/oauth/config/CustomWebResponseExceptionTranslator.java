package com.cici.oauth.config;

import com.alibaba.fastjson.JSON;
import com.cici.exception.common.ExceptionEnumImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 异常处理
 */
@Slf4j
@Component
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        HttpServletRequest request2 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String grantTypeValue = request2.getParameter("grant_type");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if ("password".equals(grantTypeValue)) {
            throw e;
        } else {
            LinkedHashMap ex = new LinkedHashMap();
            ex.put("timestamp", new Date().getTime());
            ex.put("status", HttpStatus.UNAUTHORIZED.value());
            Map detail = new HashMap();
            detail.put("code", ExceptionEnumImpl.INVALID_TOKEN.getCode());
            detail.put("message", "登录失败，请重新登录");
            ex.put("message", JSON.toJSONString(detail));
            ex.put("error", "Authentication:" + e.getMessage());
            return new ResponseEntity(ex, headers, HttpStatus.UNAUTHORIZED);
        }
    }
}
