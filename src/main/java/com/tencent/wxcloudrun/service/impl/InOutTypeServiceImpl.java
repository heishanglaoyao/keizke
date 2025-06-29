package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.InOutTypeMapper;
import com.tencent.wxcloudrun.model.InOutTypeDto;
import com.tencent.wxcloudrun.service.InOutTypeService;

import java.util.List;

public class InOutTypeServiceImpl extends ServiceImpl<InOutTypeMapper, InOutTypeDto> implements InOutTypeService {

    @Override
    public List<InOutTypeDto> listAllByName(String name) {
        return List.of();
    }
}
