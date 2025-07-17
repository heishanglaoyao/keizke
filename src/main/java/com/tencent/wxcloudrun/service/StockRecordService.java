package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.StockRecordDto;
import com.tencent.wxcloudrun.model.bo.StockRecordBo;

public interface StockRecordService extends IService<StockRecordDto> {

    public String save(StockRecordBo stockGoodBo);


}
