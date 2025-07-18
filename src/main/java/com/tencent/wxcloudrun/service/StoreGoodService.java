package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.StoreGoodDto;

import java.util.List;

public interface StoreGoodService extends IService<StoreGoodDto> {

    public int updateBatchSelective(List<StoreGoodDto> list);
}
