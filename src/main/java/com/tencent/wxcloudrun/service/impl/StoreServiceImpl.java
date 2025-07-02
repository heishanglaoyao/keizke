package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.StoreMapper;
import com.tencent.wxcloudrun.model.StoreDto;
import com.tencent.wxcloudrun.service.StoreService;

import java.util.Arrays;
import java.util.List;

public class StoreServiceImpl extends ServiceImpl<StoreMapper, StoreDto> implements StoreService {

    @Override
    public List<StoreDto> listAllByName(String name) {
        return Arrays.asList();
    }
}
