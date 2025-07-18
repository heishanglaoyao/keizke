package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.constant.StoreEnum;
import com.tencent.wxcloudrun.dao.StoreMapper;
import com.tencent.wxcloudrun.model.StoreDto;
import com.tencent.wxcloudrun.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, StoreDto> implements StoreService {

    @Override
    public List<StoreDto> listAll(String name) {
        return Arrays.stream(StoreEnum.values())
                .map(enumItem -> {
                    StoreDto storeDto = new StoreDto();
                    storeDto.setId(enumItem.getId());
                    storeDto.setName(enumItem.getName());
                    return storeDto;
                })
                .collect(Collectors.toList());
    }
}
