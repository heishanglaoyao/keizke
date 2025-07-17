package com.tencent.wxcloudrun.constant;

/**
 * 库存记录类型枚举
 */
public enum StockTypeEnum {

    IN(0, "入库"),
    OUT(1, "出库"),
    SWITCH(2, "转仓"),
    CHECK(3, "盘点"),
    ;

    StockTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StockTypeEnum getStockTypeEnumByCode(int code) {
        for (StockTypeEnum value : StockTypeEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    private int code;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
