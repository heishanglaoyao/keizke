package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.bo.GoodBo;
import com.tencent.wxcloudrun.model.qo.GoodQo;
import com.tencent.wxcloudrun.model.vo.GoodVo;

import java.util.List;

public interface GoodService extends IService<GoodDto> {
    // 自定义方法
    List<GoodDto> listAllByName(String name);

    public boolean edit(GoodBo goodBo);

    public List<GoodVo> list(GoodQo qo);

}