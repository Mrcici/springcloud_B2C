package com.cici.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cici
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DelFlagEntity<T> extends BaseEntity<T> {
    /**
     * 逻辑删除配置 [1：正常；2：删除]
     */
    @TableLogic
    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private Integer delFlag;
}
