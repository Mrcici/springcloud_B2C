package com.cici.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * An entity base class that is used for auditing purposes
 * bounded context.
 */

@Data
public class BaseEntity<T> implements Serializable {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    private Date updateDate;
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Long updateBy;
    /**
     * 版本，乐观锁
     */
    @Version
    @TableField(value = "version", fill = FieldFill.INSERT)
    private Integer version;

}
