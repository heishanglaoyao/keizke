package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson2.JSONObject;
import com.tencent.wxcloudrun.model.bo.StockGoodBo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class StockGoodService {

    public String save(StockGoodBo stockGoodBo){
        log.info("save(StockGoodBo ={})", JSONObject.toJSONString(stockGoodBo));


        return "success";
    }

}
