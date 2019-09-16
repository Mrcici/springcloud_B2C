package com.cici.oauth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：cici
 * @date ：Created in 2019/9/16 11:45
 */
@TableName(value = "role")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 786720913273205620L;

    @TableId(type = IdType.ID_WORKER)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "update_date")
    private Date updateDate;

    @TableField(value = "create_date")
    private Date createDate;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "authority")
    private String authority;

}
