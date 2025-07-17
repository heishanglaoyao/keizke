package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.GoodStoreDto;

import java.util.List;

public interface GoodStoreService extends IService<GoodStoreDto> {

    public int updateBatchSelective(List<GoodStoreDto> list);
}
