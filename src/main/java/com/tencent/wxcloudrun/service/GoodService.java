package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.bo.GoodBo;

import java.util.List;

public interface GoodService extends IService<GoodDto> {
    // 自定义方法
    List<GoodDto> listAllByName(String name);

    public boolean edit(GoodBo goodBo);
}