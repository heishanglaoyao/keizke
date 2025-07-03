package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.InOutTypeDto;
import com.tencent.wxcloudrun.model.StoreDto;

import java.util.List;

public interface InOutTypeService extends IService<InOutTypeDto> {

    // 自定义方法
    List<InOutTypeDto> listAllByName(String name);


    List<InOutTypeDto> listByType(Integer type);


}