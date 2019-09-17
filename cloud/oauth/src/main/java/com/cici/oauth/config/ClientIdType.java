package com.cici.oauth.config;

/**
 * @author 92942
 */

public enum ClientIdType {
    ADMIN_CLIENT("admin-client", "ROLE_ADMIN"),
    APP_CLIENT("app-client", "ROLE_STORE");

    private String id;

    private String authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    ClientIdType(String id) {
        this.id = id;
    }


    ClientIdType(String id, String authorities) {
        this.id = id;
        this.authorities = authorities;
    }
}
