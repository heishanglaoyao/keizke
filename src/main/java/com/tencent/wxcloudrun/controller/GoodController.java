package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.bo.GoodBo;
import com.tencent.wxcloudrun.model.qo.GoodQo;
import com.tencent.wxcloudrun.model.vo.GoodVo;
import com.tencent.wxcloudrun.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping("/detail")
    public ApiResponse<GoodDto> detail(@RequestParam Integer id) {
        return ApiResponse.ok(goodService.getById(id));
    }

    /**
     * 新增或编辑
     * @param good
     * @return
     */
    @PostMapping("/edit")
    public ApiResponse<Boolean> add(@RequestBody GoodBo good) {
        return ApiResponse.ok(goodService.edit(good));
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable Long id) {
        return ApiResponse.ok(goodService.removeById(id));
    }


    // 条件查询
    @GetMapping("/list")
    public ApiResponse<List<GoodVo>> list(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) Integer categoryId) {
        GoodQo qo =new GoodQo();
        qo.setName(name);
        qo.setCategoryId(categoryId);
        return ApiResponse.ok(goodService.list(qo));
    }

}
