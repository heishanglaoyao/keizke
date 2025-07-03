package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.GoodCategoryDto;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.bo.GoodBo;
import com.tencent.wxcloudrun.model.vo.GoodRecordVo;
import com.tencent.wxcloudrun.model.vo.OptionVo;
import com.tencent.wxcloudrun.service.GoodCategoryService;
import com.tencent.wxcloudrun.service.GoodRecordService;
import com.tencent.wxcloudrun.service.GoodService;
import com.tencent.wxcloudrun.service.SpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
商品记录管理
 */
@RestController
@RequestMapping("/goodRecord")
public class GoodRecordContoller {

    @Autowired
    private GoodCategoryService goodCategoryService;

    @Autowired
    private GoodService goodService;

    @Autowired
    private GoodRecordService recordService;


    @PostMapping("/operate")
    public ApiResponse<Boolean> operate(@RequestBody GoodRecordVo recordVo) {
        return ApiResponse.ok(recordService.operate(recordVo));
    }


    @RequestMapping("/goodOptions")
    public ApiResponse<List<OptionVo>> goodOptions() {
        return ApiResponse.ok(getGoodOptions());
    }

    private List<OptionVo> getGoodOptions() {
        List<OptionVo> options = new ArrayList<>();
        List<GoodDto> goodList = goodService.list();
        List<GoodCategoryDto> categoryDtos = goodCategoryService.list();
        for (GoodCategoryDto categoryDto : categoryDtos) {
            OptionVo option = new OptionVo();
            option.setValue(categoryDto.getId());
            option.setLabel(categoryDto.getName());
            List<OptionVo> children = new ArrayList<>();
            for (GoodDto good : goodList) {
                if (good.getCategoryId().equals(categoryDto.getId())) {
                    OptionVo child = new OptionVo();
                    child.setParentValue(categoryDto.getId());
                    child.setValue(good.getId());
                    child.setLabel(good.getName());
                    children.add(child);
                }
            }
            option.setChildren(children);
            options.add(option);
        }
        return options;
    }

}
