package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.StoreDto;

import java.util.List;

public interface StoreMapper extends BaseMapper<StoreDto> {
    // 可以自定义方法
    List<StoreDto> selectAllByName(String name);
}