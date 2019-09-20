package com.cici.oauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * OauthRole Entity
 */
@Entity
//@Data
@Table(name = "user_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 786720913273205620L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private long updateDate;

    private long createDate;

    private String remark;
    private String authority;
    @ManyToMany(mappedBy = "roles")
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Account> users;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Account> getUsers() {
        return users;
    }

    public void setUsers(List<Account> users) {
        this.users = users;
    }

}
