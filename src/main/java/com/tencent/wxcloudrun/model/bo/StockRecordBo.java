package com.tencent.wxcloudrun.model.bo;

import lombok.Data;

import java.util.List;

/**
 * 出入库记录对象
 */
@Data
public class StockRecordBo {

    /**
     * 出入库日期
     */
    private String dateStr;
    /**
     * 仓库id
     */
    private Integer storeId;

    /**
     * 转仓时，转入的仓库id
     */
    private Integer toStoreId;

    /**
     * 0-出库
     * 1-入库
     * 2-转库
     * 3-盘点
     */
    private int stockType;

    /**
     * 采购类型id
     */
    private Integer typeId;

    /**
     * 备注
     */
    private String notes;

    /**
     * 选择商品
     */
    private List<Goods> selectedGoods;


    @Data
    public static class Goods {
        private int id;
        private String name;
        /**
         * 选择数量规格
         */
        private List<UnitQuantity> unitQuantitiesList;
    }

    @Data
    public static class UnitQuantity {
        private String name;
        private int value;
    }
}


