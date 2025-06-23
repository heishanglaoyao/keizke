package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.GoodCategoryMapper;
import com.tencent.wxcloudrun.model.GoodCategoryDto;
import com.tencent.wxcloudrun.service.GoodCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodCategoryServiceImpl extends ServiceImpl<GoodCategoryMapper, GoodCategoryDto> implements GoodCategoryService {

    @Override
    public List<GoodCategoryDto> listAllByName(String name) {
        QueryWrapper<GoodCategoryDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(GoodCategoryDto::getName, name);
        return this.list(queryWrapper);
    }
}