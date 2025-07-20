package com.tencent.wxcloudrun.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now(ZONE_ID);
        this.strictInsertFill(metaObject, "createTime", () -> now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTime", () -> now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now(ZONE_ID);
        this.strictUpdateFill(metaObject, "updateTime", () -> now, LocalDateTime.class);
    }
}
