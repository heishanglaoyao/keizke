package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface GoodMapper extends BaseMapper<GoodDto> {
    // 可以自定义方法
    List<GoodDto> slectAllByName(String name);

}