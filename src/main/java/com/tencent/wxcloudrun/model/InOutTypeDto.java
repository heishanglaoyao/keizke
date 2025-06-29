package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 出入库类型
 */
@Data
@SuperBuilder
@TableName("t_inout_type")
public class InOutTypeDto extends BaseDto{


    private Integer type;

}
