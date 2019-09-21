package com.cici.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;
import java.util.Optional;

public final class SecurityUtils {
    /**
     * Utility class for Spring Security.
     */

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    /**
     * 查询自定义的字段
     *
     * @param name 字段key
     * @return
     */
    public static String getJwtDecodedDetails(String name) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
            Map<String, String> details = (Map<String, String>) auth2AuthenticationDetails.getDecodedDetails();
            return Optional.ofNullable(details.get(name)).orElse(null);
        }
        return null;
    }

    /**
     * 获取客户端 id,若为null则返回unknown
     *
     * @return
     */
    public static String getCurrentClientId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null & authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            return oAuth2Authentication.getOAuth2Request().getClientId();
        }
        return "unknown";
    }

    /**
     * Get the JWT of the current user.
     *
     * @return the JWT of the current user
     */
    public static String getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String) {
            return (String) authentication.getCredentials();
        }
        return null;
    }

    //检查是否是匿名用户
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS));
        }
        return false;
    }

    //判断权限
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
        }
        return false;
    }
}

