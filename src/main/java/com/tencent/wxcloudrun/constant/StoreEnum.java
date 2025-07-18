package com.tencent.wxcloudrun.constant;

import java.util.List;
import java.util.Map;

public enum StoreEnum {


    COLD_STORAGE(1, "冷库"),
    SHOP_STORAGE(2, "店里"),
    ;

    StoreEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StoreEnum getStoreEnum(int id) {
        for (StoreEnum value : StoreEnum.values()) {
            if (value.getId() == id) {
                return value;
            }
        }
        return null;
    }


    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

