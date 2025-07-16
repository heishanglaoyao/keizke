package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author: lzy
 * @description: 库存商品记录
 */
@Data
//@SuperBuilder
//@NoArgsConstructor
//@TableName("t_stock_record")
public class StockRecordDto extends BaseDto{

    private String date;
    /**
     * json 保存商品信息
     */
    private String selectGoods;

}
