package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.GoodCategoryDto;
import com.tencent.wxcloudrun.service.SpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specs")
public class SpecsController {

    @Autowired
    private SpecsService specsService;

    @GetMapping("/list")
    public ApiResponse<List<GoodCategoryDto>> list() {
        return ApiResponse.ok(specsService.list());
    }

}
