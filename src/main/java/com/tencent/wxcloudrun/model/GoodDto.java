package com.tencent.wxcloudrun.model;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.tencent.wxcloudrun.utils.GoodUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 商品表
 */
@Data
@SuperBuilder
@NoArgsConstructor
@TableName("t_good")
public class GoodDto extends BaseDto{

    /**
     * 图片
     */
    private String imgs;

    /**
     * 分类
     */
    private Integer categoryId;


    /**
     * 规格 id
     */
    private Integer specsId;


    /**
     * 条形码
     */
    private String barCode;

    /**
     * 进价
     */
    private Float inPrice;

    /**
     * 库存预警
     */
    private int warningNum;

    /**
     * 库存数量
     */
    private int nums;

    private String unitNames;

    private String unitVals;

    private String notes;

    private String specsName;


    @TableField(exist = false)
    private List<String> unitNameArray;

    @TableField(exist = false)
    private List<Integer> unitValArray;

    public void setUnit(){
        List<String> unitNameArray = new ArrayList<>();
        List<Integer> unitValArray = new ArrayList<>();
        GoodUtils.parseString(this.getSpecsName(),
                unitValArray,
                unitNameArray);
        this.setUnitNames(JSONArray.toJSONString(unitNameArray));
        this.setUnitVals(JSONArray.toJSONString(unitValArray));

    }

    public List<String> getUnitNameArray() {
        if(StringUtils.isEmpty(this.unitNames)){
            return Collections.emptyList();
        }
        return JSONArray.parseArray(this.unitNames, String.class);
    }


    public List<Integer> getUnitValArray() {
        if(StringUtils.isEmpty(this.unitVals)){
            return Collections.emptyList();
        }
        return JSONArray.parseArray(this.unitVals, Integer.class);
    }


}
