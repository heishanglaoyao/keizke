package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@TableName("t_good_category")
public class GoodCategoryDto extends BaseDto{

    @TableField("order_id")
    private Integer orderId;

    public GoodCategoryDto() {
        // 无参构造函数
    }
}
