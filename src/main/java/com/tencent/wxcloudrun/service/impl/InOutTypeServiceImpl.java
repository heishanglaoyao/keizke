package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.InOutTypeMapper;
import com.tencent.wxcloudrun.model.GoodCategoryDto;
import com.tencent.wxcloudrun.model.InOutTypeDto;
import com.tencent.wxcloudrun.service.InOutTypeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InOutTypeServiceImpl extends ServiceImpl<InOutTypeMapper, InOutTypeDto> implements InOutTypeService {

    @Override
    public List<InOutTypeDto> listAllByName(String name) {
        return Arrays.asList();
    }

    @Override
    public List<InOutTypeDto> listByType(Integer type) {
        if(type == null){
            return this.list();
        }else{
            QueryWrapper<InOutTypeDto> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(InOutTypeDto::getType, type);
            return this.list(queryWrapper);
        }
    }
}
