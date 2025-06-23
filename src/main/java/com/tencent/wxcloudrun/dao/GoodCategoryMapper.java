package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.GoodCategoryDto;

import java.util.List;

public interface GoodCategoryMapper extends BaseMapper<GoodCategoryDto> {
    // 可以自定义方法
    List<GoodCategoryDto> selectAllByName(String name);

}