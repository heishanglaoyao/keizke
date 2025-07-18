package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.StoreGoodMapper;
import com.tencent.wxcloudrun.model.StoreGoodDto;
import com.tencent.wxcloudrun.service.StoreGoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StoreGoodServiceImpl extends ServiceImpl<StoreGoodMapper, StoreGoodDto> implements StoreGoodService {

    public int updateBatchSelective(List<StoreGoodDto> list){
       return this.getBaseMapper().updateBatchSelective(list);
    }

}
