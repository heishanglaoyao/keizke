package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.InOutTypeDto;
import com.tencent.wxcloudrun.service.InOutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inOutTpye")
public class InOutTypeContoller {

    @Autowired
    private InOutTypeService inOutTypeService;

    @GetMapping("/list")
    public ApiResponse<List<InOutTypeDto>> list(@RequestParam(required = false) Integer type) {

        return ApiResponse.ok(inOutTypeService.listByType(type));
    }
}
