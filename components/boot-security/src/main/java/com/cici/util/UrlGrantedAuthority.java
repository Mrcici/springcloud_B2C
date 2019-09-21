package com.cici.util;
import org.springframework.security.core.GrantedAuthority;


public class UrlGrantedAuthority implements GrantedAuthority {

    private String httpMethod;
    private String url;

    public UrlGrantedAuthority(String httpMethod, String url) {
        this.httpMethod = httpMethod;
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlGrantedAuthority that = (UrlGrantedAuthority) o;
        if (httpMethod != null ? !httpMethod.equals(that.httpMethod) : that.httpMethod != null) return false;
        return url != null ? url.equals(that.url) : that.url == null;
    }

    @Override
    public int hashCode() {
        int result = httpMethod != null ? httpMethod.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
    @Override
    public String getAuthority() {
        return this.url + ";" + this.httpMethod;
    }
}
