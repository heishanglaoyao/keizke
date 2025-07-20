package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.constant.StoreEnum;
import com.tencent.wxcloudrun.dao.GoodMapper;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.model.StoreGoodDto;
import com.tencent.wxcloudrun.model.bo.GoodBo;
import com.tencent.wxcloudrun.model.qo.GoodQo;
import com.tencent.wxcloudrun.model.vo.GoodVo;
import com.tencent.wxcloudrun.model.vo.SpecsVo;
import com.tencent.wxcloudrun.service.GoodService;
import com.tencent.wxcloudrun.service.SpecsService;
import com.tencent.wxcloudrun.service.StoreGoodService;
import com.tencent.wxcloudrun.service.StoreService;
import com.tencent.wxcloudrun.utils.ConvertUtils;
import com.tencent.wxcloudrun.utils.GoodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, GoodDto> implements GoodService {

    @Autowired
    SpecsService specsService;

    @Autowired
    StoreGoodService storeGoodService;

    @Autowired
    StoreService storeService;

    @Override
    public List<GoodDto> listAllByName(String name) {
        QueryWrapper<GoodDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(GoodDto::getName, name);
        return this.list(queryWrapper);
    }

    public String edit(GoodBo goodBo){
        String errMsg = null;
        try {
            GoodDto goodDto = ConvertUtils.copyProperties(GoodDto.class,goodBo);
            goodDto.setUnit();
            this.saveOrUpdate(goodDto);
        }catch (Exception e){
            errMsg = e.getMessage();
        }
        return errMsg;
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
        List<StoreGoodDto> storeGoodDtos = storeGoodService.list(null);
        goodVos = transGoodVos(dtos,storeGoodDtos);
        return goodVos;
    }

    public List<GoodVo> recordlist(GoodQo qo){
        List<GoodVo> goodVos = new ArrayList<>();
        //预警记录查询

        boolean isWarning = (qo.getQueryType() != null && qo.getQueryType() == 1);
        List<StoreGoodDto> storeGoodDtos = storeGoodService.list(null);
        if(org.apache.commons.collections4.CollectionUtils.isEmpty(storeGoodDtos)){
            return goodVos;
        }
        List<Integer> uniqueGoodIds = storeGoodDtos.stream()
                .map(StoreGoodDto::getGoodId)
                .distinct()
                .collect(Collectors.toList());
        List<GoodDto> dtos = this.listByIds(uniqueGoodIds);
        Map<Integer, List<StoreGoodDto>> groupedByStoreId = storeGoodDtos.stream()
                .collect(Collectors.groupingBy(StoreGoodDto::getStoreId));
        //不同的库类型 去封装商品信息
        for(List<StoreGoodDto> storeGoodDtos_ :groupedByStoreId.values()){
            List<Integer> goodIds_ =storeGoodDtos_.stream().map(sgd -> sgd.getGoodId()).collect(Collectors.toList());
            if(org.apache.commons.collections4.CollectionUtils.isEmpty(goodIds_)) {
                continue;
            }
            List<GoodVo> goodVos_ = transGoodVos(
                    dtos.stream().filter(
                            vo -> goodIds_.contains(vo.getId())).collect(Collectors.toList())
                    , storeGoodDtos_
            );
            goodVos.addAll(goodVos_);
        }
        if(isWarning) {
            goodVos = goodVos.parallelStream().filter(
                    vo -> vo.getNum() <= vo.getWarningRealNum()).collect(Collectors.toList());
        }
        return goodVos;
    }

    private List<GoodVo> transGoodVos(List<GoodDto> dtos, List<StoreGoodDto> storeGoodDtos){
        if(org.apache.commons.collections4.CollectionUtils.isEmpty(dtos)){
            return new ArrayList<>();
        }
        List<GoodVo> goodVos = dtos.parallelStream()
                .map(dto -> {
//                    SpecsVo specsVo = specsVos.stream().filter(x->x.getId().equals(dto.getSpecsId())).findFirst().orElse(null);
                    GoodVo vo = GoodVo.trasform(dto);
                    StoreGoodDto storeGoodDto_ = storeGoodDtos.stream().filter(
                            x->x.getGoodId().equals(dto.getId())).findFirst().orElse(null);
                    if(storeGoodDto_ != null ){
                        vo.setStoreId(storeGoodDto_.getStoreId());
                        vo.setNum(storeGoodDto_.getNums());
                        vo.setWarningRealNum(GoodUtils.calWarningNum(vo.getWarningNum(),dto.getUnitValArray()));
                        vo.setNumStr(GoodUtils.transUnitStr(storeGoodDto_.getNums(),
                                dto.getUnitNameArray(), dto.getUnitValArray()));
                        vo.setStoreName(StoreEnum.getStoreEnum(storeGoodDto_.getStoreId()).getName());
                    }
                    // 其他属性赋值...
                    return vo;
                })
                .collect(Collectors.toList());
        return goodVos;
    }

}