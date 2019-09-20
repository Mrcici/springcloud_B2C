package com.cici.oauth.domain;

import com.cici.oauth.domain.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "account")
@JsonPropertyOrder({"id", "username", "type"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class Account  implements UserDetails {
    @Id
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String payPassword;
    @Enumerated(EnumType.ORDINAL)

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    @JoinTable(
            name = "accounts_roles",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
//    private Long expiredTime;

    @CreatedDate
    @JsonIgnore
    private Date createDate;

    @LastModifiedDate
    @JsonIgnore
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//
//    public Long getExpiredTime() {
//        return expiredTime;
//    }
//
//    public void setExpiredTime(Long expiredTime) {
//        this.expiredTime = expiredTime;
//    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authorities = this.getRoles().stream().map(Role::getAuthority).collect(Collectors.toList());
//        Authorities.addAll(organizations.stream().map(Organization::getRole).collect(Collectors.toList()));
        return mapToGrantedAuthorities(authorities);
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {

//        log.debug(getExpiredTime().toString());
//        return getExpiredTime() == 1 | getExpiredTime() > new DateTime().getMillis();
        return true;
    }

    // 账号是否未过期
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {

        return true;

    }

    // 账号是否未锁定
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
