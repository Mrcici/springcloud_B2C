package com.cici.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomOAuth2AuthenticationEntryPoint extends AbstractOAuth2SecurityExceptionHandler implements
        AuthenticationEntryPoint {

    private String typeName = OAuth2AccessToken.BEARER_TYPE;

    private String realmName = "oauth";
    private int ERROR_CODE = 1000002;

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
    protected ResponseEntity<?> enhanceResponse(ResponseEntity<?> response,
                                                Exception exception) {
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

        // Custom exception body
        if (exception instanceof AuthenticationException) {


            Map ex = new HashMap();
            ex.put("timestamp", new Date().getTime());
            ex.put("status", response.getStatusCode().value());
            ex.put("code", ERROR_CODE);
            Map detail = new HashMap();
            detail.put("code", ERROR_CODE);
            detail.put("message", "请重新登录再试");
            ex.put("message", JSON.toJSONString(detail));
            ex.put("error", "Authentication," + exception.getMessage());
            log.info(exception.getMessage());
            return new ResponseEntity(ex, update, response.getStatusCode());
        }

        return new ResponseEntity<>(response.getBody(), update, response.getStatusCode());
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
