package com.tencent.wxcloudrun.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
public class SpecsVo {

    private Integer id;

    private String name;

    private List<Integer> unitVals;

    private List<String> unitNames;


    public static SpecsVo generateSpecsVo(
            String name, List<Integer> unitVals, List<String> unitNames){
        SpecsVo specsVo = new SpecsVo();
        specsVo.setName(name);
        specsVo.setUnitNames(unitNames);
        specsVo.setUnitVals(unitVals);
        return specsVo;
    }
}
