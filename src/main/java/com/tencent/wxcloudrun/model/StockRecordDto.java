package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tencent.wxcloudrun.model.bo.StockRecordBo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author: lzy
 * @description: 库存商品记录
 */
@Data
@SuperBuilder
@NoArgsConstructor
@TableName("t_stock_record")
public class StockRecordDto extends BaseDto{

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
    private String selectedGoodJson;

    private String extInfo;

}
