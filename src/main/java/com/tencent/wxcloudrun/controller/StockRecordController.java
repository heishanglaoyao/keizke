package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.bo.StockRecordBo;
import com.tencent.wxcloudrun.service.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 出/入库操作
 */
@RestController
@RequestMapping("/stockRecord")
public class StockRecordController {


    @Autowired
    private StockRecordService stockRecordService;

    @PostMapping()
    public ApiResponse<Boolean> add(@RequestBody StockRecordBo stockRecordBo) {
        return ApiResponse.ok4ErrMsg(stockRecordService.save(stockRecordBo));
    }


}
