package com.tencent.wxcloudrun;

public enum GoodRecordOperateEnum {

    IN(1,"入库"),
    OUT(2,"出库"),
    TRANS(3,"转仓"),
    CROSS(4,"转仓"),
    ;

    private int type;
    private String desc;
    GoodRecordOperateEnum(int type,String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static GoodRecordOperateEnum getByType(Integer opType) {
        for (GoodRecordOperateEnum value : values()) {
            if (value.type == opType) {
                return value;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
