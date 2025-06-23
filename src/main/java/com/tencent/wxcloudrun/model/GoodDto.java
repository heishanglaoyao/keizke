package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_good")
public class GoodDto extends BaseDto{


    public String name;

    /**
     * 图片
     */
    private String imgs;

    /**
     * 分类
     */
    private Long categoryId;

    /**
     * 单位
     */
    private String unit;

    /**
     * 规格
     */
    private String standards;

    /**
     * 条形码
     */
    private String barCode;

    /**
     * 进价
     */
    private Float inPrace;

    /**
     * 库存预警
     */
    private int warningNum;

}
