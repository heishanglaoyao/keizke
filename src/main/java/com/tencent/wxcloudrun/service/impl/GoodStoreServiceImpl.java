package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.GoodStoreMapper;
import com.tencent.wxcloudrun.model.GoodStoreDto;
import com.tencent.wxcloudrun.service.GoodStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodStoreServiceImpl extends ServiceImpl<GoodStoreMapper, GoodStoreDto> implements GoodStoreService {

    public int updateBatchSelective(List<GoodStoreDto> list){
       return this.getBaseMapper().updateBatchSelective(list);
    }

}
