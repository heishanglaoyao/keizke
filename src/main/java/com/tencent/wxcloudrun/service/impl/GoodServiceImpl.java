package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.GoodMapper;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.bo.GoodBo;
import com.tencent.wxcloudrun.service.GoodService;
import com.tencent.wxcloudrun.utils.ConvertUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodDto> implements GoodService {

    @Override
    public List<GoodDto> listAllByName(String name) {
        QueryWrapper<GoodDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(GoodDto::getName, name);
        return this.list(queryWrapper);
    }

    public boolean edit(GoodBo goodBo){
        GoodDto goodDto = ConvertUtils.copyProperties(GoodDto.class,goodBo);
        return this.saveOrUpdate(goodDto);
    }
}