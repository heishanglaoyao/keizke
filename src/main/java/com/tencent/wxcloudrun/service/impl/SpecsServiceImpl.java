package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.SpecsMapper;
import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.model.vo.SpecsVo;
import com.tencent.wxcloudrun.service.SpecsService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecsServiceImpl extends ServiceImpl<SpecsMapper, SpecsDto> implements SpecsService {

    @Override
    public List<SpecsVo> selectBatchIds(List<Integer> ids) {
        List<SpecsDto> specsDtos = this.getBaseMapper().selectBatchIds(ids);
        return tansVos(specsDtos);
    }

    @Override
    public List<SpecsVo> selectList() {
        List<SpecsDto> specsDtos = this.getBaseMapper().selectList(null);
        return tansVos(specsDtos);
    }

    private List<SpecsVo> tansVos(List<SpecsDto> specsDtos){
        if(CollectionUtils.isEmpty(specsDtos)){
            Collections.emptyList();
        }
        return specsDtos.stream().map(dto -> {
            SpecsVo vo = new SpecsVo();
            vo.setId(dto.getId());
            vo.setName(dto.getName());
            vo.setUnitNames(JSONArray.parseArray(dto.getUnitName(), String.class));
            vo.setUnitVals(JSONArray.parseArray(dto.getUnitVal(), Integer.class));
            return vo;
        }).collect(Collectors.toList());
    }

}
