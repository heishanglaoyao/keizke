package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.GoodCategoryDto;
import com.tencent.wxcloudrun.service.GoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodCategory")
public class GoodCategoryContoller {

    @Autowired
    private GoodCategoryService goodCategoryService;

    @PostMapping("/add")
    public ApiResponse<Boolean> add(@RequestBody GoodCategoryDto categoryDto) {
        return ApiResponse.ok(goodCategoryService.save(categoryDto));
    }

    @PostMapping("/update")
    public ApiResponse<Boolean> update(@RequestBody GoodCategoryDto categoryDto) {
        return ApiResponse.ok(goodCategoryService.updateById(categoryDto));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable Long id) {
        return ApiResponse.ok(goodCategoryService.removeById(id));
    }

    // 查询所有
    @GetMapping("/list")
    public ApiResponse<List<GoodCategoryDto>> list() {
        return ApiResponse.ok(goodCategoryService.list());
    }
}
