package com.cici;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author ：cici
 * @date ：Created in 2019/9/11 17:48
 */
@Configuration
@Slf4j
public class MyMetaObjectHandler  implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("==============");
        Object createDate = getFieldValByName("createDate", metaObject);
        Object version = getFieldValByName("version", metaObject);
        Object updateDate = getFieldValByName("updateDate", metaObject);
        Object delFlag = getFieldValByName("delFlag", metaObject);

        if (createDate == null) {
            this.setInsertFieldValByName("createDate", new Date(), metaObject);
        }
        if (updateDate == null) {
            this.setInsertFieldValByName("updateDate", new Date(), metaObject);
        }
        if (version == null) {
            this.setInsertFieldValByName("version", 1, metaObject);
        }
        if (delFlag == null) {
            this.setInsertFieldValByName("delFlag", 0, metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setInsertFieldValByName("updateDate", new Date(), metaObject);

    }
}