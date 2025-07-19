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


}
