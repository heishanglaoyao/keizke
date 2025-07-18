package com.tencent.wxcloudrun.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 商品库存关系表
 */
@Data
@SuperBuilder
@NoArgsConstructor
@TableName("t_store_good")
public class StoreGoodDto extends BaseDto{

    private Integer goodId;

    private Integer storeId;

    private Integer nums;

    /**
     * 总价格(分)
     */
    private Long priceInCent;

    private String extInfo;

    /**
     *页面展示库存规格数量
     */
    @TableField(exist = false)
    private String showNums;

    private String getShowText(){
        return "商品ID:" + goodId + "仓库ID:" + storeId + "库存:" + nums + "价格:" + priceInCent;
    }
}
