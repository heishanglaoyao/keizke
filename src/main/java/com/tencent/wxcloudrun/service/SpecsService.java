package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.model.vo.SpecsVo;

import java.util.List;

public interface SpecsService extends IService<SpecsDto> {


    List<SpecsVo> selectBatchIds(List<Integer> ids);

    List<SpecsVo> selectList();


}
