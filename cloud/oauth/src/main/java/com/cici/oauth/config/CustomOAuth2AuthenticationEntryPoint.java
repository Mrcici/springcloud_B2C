package com.cici.oauth.config;

import com.alibaba.fastjson.JSON;
import com.cici.exception.common.ExceptionEnumImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 异常处理
 */
@Component
public class CustomOAuth2AuthenticationEntryPoint extends AbstractOAuth2SecurityExceptionHandler implements
        AuthenticationEntryPoint {

    private String typeName = OAuth2AccessToken.BEARER_TYPE;

    private String realmName = "oauth";

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        doHandle(request, response, authException);
    }

    @Override
    protected ResponseEntity<OAuth2Exception> enhanceResponse(ResponseEntity<?> response, Exception exception) {
        HttpHeaders headers = response.getHeaders();
        String existing = null;
        if (headers.containsKey("WWW-Authenticate")) {
            existing = extractTypePrefix(headers.getFirst("WWW-Authenticate"));
        }
        StringBuilder builder = new StringBuilder();
        builder.append(typeName + " ");
        builder.append("realm=\"" + realmName + "\"");
        if (existing != null) {
            builder.append(", " + existing);
        }
        HttpHeaders update = new HttpHeaders();
        update.putAll(response.getHeaders());
        update.set("WWW-Authenticate", builder.toString());

        LinkedHashMap ex = new LinkedHashMap();
        ex.put("timestamp", System.currentTimeMillis());
        ex.put("error", "Authentication:" + exception.getMessage());
        ex.put("status", HttpStatus.UNAUTHORIZED.value());
        Map detail = new HashMap();
        detail.put("code", ExceptionEnumImpl.INVALID_TOKEN.getCode());
        detail.put("message", "登录失败，请重新登录");
        ex.put("message", JSON.toJSONString(detail));
        return new ResponseEntity(ex, update, response.getStatusCode());
    }


    private String extractTypePrefix(String header) {
        String existing = header;
        String[] tokens = existing.split(" +");
        if (tokens.length > 1 && !tokens[0].endsWith(",")) {
            existing = StringUtils.arrayToDelimitedString(tokens, " ").substring(existing.indexOf(" ") + 1);
        }
        return existing;
    }

}
