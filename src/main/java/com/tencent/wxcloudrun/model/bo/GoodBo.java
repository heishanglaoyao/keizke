package com.tencent.wxcloudrun.model.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tencent.wxcloudrun.model.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 商品表
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class GoodBo{

    private Integer id;

    private String name;

    /**
     * 图片
     */
    private String imgs;

    /**
     * 分类
     */
    private Integer categoryId;


    /**
     * 规格 id
     */
    private Integer specsId;


    /**
     * 条形码
     */
    private String barCode;

    /**
     * 进价
     */
    private Float inPrice;

    /**
     * 库存预警
     */
    private int warningNum;

}
