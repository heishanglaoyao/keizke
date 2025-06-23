package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_good_category")
public class GoodCategoryDto extends BaseDto{

    private String name;
}
