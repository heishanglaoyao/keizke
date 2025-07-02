package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.GoodCategoryDto;

import java.util.List;

public interface GoodCategoryService extends IService<GoodCategoryDto> {
    // 自定义方法
    List<GoodCategoryDto> listAllByName(String name);

    public List<GoodCategoryDto> list();
}