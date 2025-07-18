package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.StoreDto;

import java.util.List;

public interface StoreService extends IService<StoreDto> {

    // 自定义方法
    List<StoreDto> listAll(String name);
}