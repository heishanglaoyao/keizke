package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.GoodCategoryDto;
import com.tencent.wxcloudrun.model.bo.StockGoodBo;
import com.tencent.wxcloudrun.service.StockGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 出/入库操作
 */
@RestController
@RequestMapping("/stockGood")
public class StockGoodController {


    @Autowired
    private StockGoodService stockGoodService;

    @PostMapping()
    public ApiResponse<Boolean> add(@RequestBody StockGoodBo stockGoodBo) {
        return ApiResponse.ok(stockGoodService.save(stockGoodBo));
    }



}
