package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user") // 对应数据库表名
public class UserDto extends BaseDto{
    @TableId(type = IdType.AUTO) // 主键自增
    private Long id;
    private String name;
    private Integer age;
    private String email;

}
