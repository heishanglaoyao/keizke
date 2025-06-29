package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.InOutTypeDto;

import java.util.List;

public interface InOutTypeMapper extends BaseMapper<InOutTypeDto> {
    // 可以自定义方法
    List<InOutTypeDto> selectAllByName(String name);
}