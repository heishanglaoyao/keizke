package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.UserDto;
import com.tencent.wxcloudrun.model.qo.GoodQo;
import com.tencent.wxcloudrun.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @PostMapping("/add")
    public ApiResponse<Boolean> add(@RequestBody GoodDto good) {
        return ApiResponse.ok(goodService.save(good));
    }

    @PostMapping("/update")
    public ApiResponse<Boolean> update(@RequestBody GoodDto good) {
        return ApiResponse.ok(goodService.updateById(good));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable Long id) {
        return ApiResponse.ok(goodService.removeById(id));
    }

    // 查询所有
    @GetMapping("/list")
    public ApiResponse<List<GoodDto>> list() {
        return ApiResponse.ok(goodService.list());
    }

    // 条件查询
    @GetMapping("/query")
    public ApiResponse<List<GoodDto>> query(@RequestParam(required = false) Integer categoryId) {
        LambdaQueryWrapper<GoodDto> queryWrapper = new LambdaQueryWrapper<>();
        if (categoryId != null) {
            queryWrapper.eq(GoodDto::getCategoryId, categoryId);
        }
        return ApiResponse.ok(goodService.list(queryWrapper));
    }

}
