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

    @TableField("level")
    private int level;

    @TableField("unit_val_0")
    private int unitVal0;
    @TableField("unit_name_0")
    private String unitNme0;
    @TableField("unit_val_1")
    private int unitVal1;
    @TableField("unit_name_1")
    private String unitName1;
    @TableField("unit_val_2")
    private int unitVal2;
    @TableField("unit_name_2")
    private String unitName2;

}
