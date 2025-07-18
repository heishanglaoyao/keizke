package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.GoodMapper;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.model.StoreGoodDto;
import com.tencent.wxcloudrun.model.bo.GoodBo;
import com.tencent.wxcloudrun.model.qo.GoodQo;
import com.tencent.wxcloudrun.model.vo.GoodVo;
import com.tencent.wxcloudrun.service.GoodService;
import com.tencent.wxcloudrun.service.SpecsService;
import com.tencent.wxcloudrun.service.StoreGoodService;
import com.tencent.wxcloudrun.utils.ConvertUtils;
import com.tencent.wxcloudrun.utils.GoodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodDto> implements GoodService {

    @Autowired
    SpecsService specsService;

    @Autowired
    StoreGoodService storeGoodService;

    @Override
    public List<GoodDto> listAllByName(String name) {
        QueryWrapper<GoodDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(GoodDto::getName, name);
        return this.list(queryWrapper);
    }

    public boolean edit(GoodBo goodBo){
        GoodDto goodDto = ConvertUtils.copyProperties(GoodDto.class,goodBo);
        return this.saveOrUpdate(goodDto);
    }

    public List<GoodVo> list(GoodQo qo){
        List<GoodVo> goodVos = new ArrayList<>();
        LambdaQueryWrapper<GoodDto> queryWrapper = new LambdaQueryWrapper<>();
        if (qo.getCategoryId() != null) {
            queryWrapper.eq(GoodDto::getCategoryId, qo.getCategoryId());
        }
        if (qo.getName() != null) {
            queryWrapper.eq(GoodDto::getName, qo.getName());
        }
        if (qo.getId() != null) {
            queryWrapper.eq(GoodDto::getId, qo.getId());
        }
        List<GoodDto> dtos = this.list(queryWrapper);
        if(CollectionUtils.isEmpty(dtos)){
            return goodVos;
        }
        List<SpecsDto> specsDtos = specsService.getBaseMapper().selectBatchIds(
                dtos.stream().map(GoodDto::getSpecsId).collect(Collectors.toList())
        );
        List<StoreGoodDto> storeGoodDtos = storeGoodService.list(null);
        goodVos = dtos.parallelStream()
                .map(dto -> {
                    SpecsDto specsDto = specsDtos.stream().filter(x->x.getId().equals(dto.getSpecsId())).findFirst().orElse(null);
                    GoodVo vo = GoodVo.trasform(dto,specsDto);
                    List<StoreGoodDto> storeDtos_ = storeGoodDtos.stream().filter(x->x.getGoodId().equals(dto.getId())).collect(Collectors.toList());
                    storeDtos_.parallelStream().forEach(storeDto -> {
                        storeDto.setShowNums(GoodUtils.transUnitStr(storeDto.getNums(),specsDto));
                    });
                    vo.setStoreGoods(storeDtos_);
                    // 其他属性赋值...
                    return vo;
                })
                .collect(Collectors.toList());
        return goodVos;
    }

}