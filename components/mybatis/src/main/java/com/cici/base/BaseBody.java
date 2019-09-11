package com.cici.base;

import com.cici.tools.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;

@Data
public class BaseBody<T> implements Serializable {
    @JsonProperty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @JsonProperty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
    @JsonProperty
    private Long createBy;
    @JsonProperty
    private Long updateBy;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 是否删除 1正常；2删除
     */
    @JsonProperty
    private Integer delFlag;


    public T convertFor(String... ignoreFields) {
        //获得当前类带有泛型类型的父类
        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得运行期的泛型类型
        Class<T> clazz = (Class<T>) ptClass.getActualTypeArguments()[0];
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //属性值拷贝
        BeanUtils.copy(this, t, ignoreFields);
        return t;

    }

}
