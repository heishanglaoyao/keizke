package com.tencent.wxcloudrun.model.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OptionVo {

    private Integer value;

    private String label;

    private Integer parentValue = 0;

    private List<OptionVo> children = new ArrayList<>();

}
