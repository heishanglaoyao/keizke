package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 仓库
 */
@Data
@SuperBuilder
@TableName("t_store")
public class StoreDto extends BaseDto{

}
