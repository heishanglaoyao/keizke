package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // 或者在主类上使用 @MapperScan 扫描整个包
public interface UserMapper extends BaseMapper<UserDto> {
    // 可以自定义方法
    List<UserDto> selectAllByName(String name);
}