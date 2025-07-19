package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 商品规格
 */
@Data
@SuperBuilder
@NoArgsConstructor
@TableName("t_specs")
public class SpecsDto extends BaseDto{

    @TableField("unit_val")
    private String unitVal;
    @TableField("unit_name")
    private String unitName;

}
