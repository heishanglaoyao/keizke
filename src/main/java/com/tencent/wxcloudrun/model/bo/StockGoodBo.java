package com.tencent.wxcloudrun.model.bo;

import lombok.Data;

import java.util.List;

/**
 * 出入库记录对象
 */
@Data
public class StockGoodBo {

    /**
     * 出入库日期
     */
    private String date;
    /**
     * 仓库id
     */
    private Integer warehouseId;

    /**
     * 0-出库
     * 1-入库
     */
    private int stockType;

    /**
     * 采购类型id
     */
    private Integer typeId;
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


