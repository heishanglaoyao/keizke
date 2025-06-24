package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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


    // 条件查询
    @GetMapping("/list")
    public ApiResponse<List<GoodDto>> list(@RequestParam(required = false) Integer categoryId,
                                           @RequestParam(required = false) String name) {
        LambdaQueryWrapper<GoodDto> queryWrapper = new LambdaQueryWrapper<>();
        if (categoryId != null) {
            queryWrapper.eq(GoodDto::getCategoryId, categoryId);
        }
        if(StringUtils.isNotBlank(name)){
            queryWrapper.eq(GoodDto::getName, name);
        }
        return ApiResponse.ok(goodService.list(queryWrapper));
    }

}
