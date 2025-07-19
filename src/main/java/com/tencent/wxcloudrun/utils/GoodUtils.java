package com.tencent.wxcloudrun.utils;

import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.model.bo.StockRecordBo;
import com.tencent.wxcloudrun.model.vo.SpecsVo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GoodUtils {


    public static final String SPECS_SEPARATORS = " ";

    /**
     * 数量 转换 展示规格信息
     * @param nums
     * @param specsDto
     * @return
     */
    public static String transUnitStr(int nums, SpecsVo specs){
        // 1箱 24包 10 个
        int[] unit =  new int[3];
        int level = specs.getUnitNames().size();
        if(level ==1){
            return nums + ""+specs.getUnitNames().get(0);
        }
        if(level ==2){
            int[] unitArr = getUnitArr(nums,specs.getUnitVals().get(1));
            return unitArr[1] + ""+specs.getUnitNames().get(0) +SPECS_SEPARATORS+
                    unitArr[0] + ""+specs.getUnitNames().get(1);
        }
        if(level == 3){
            int[] unitArr = getUnitArr(nums,
                    specs.getUnitVals().get(1) * specs.getUnitVals().get(2));
            int val_2 = unitArr[1];
            unitArr = getUnitArr(unitArr[0],specs.getUnitVals().get(2));
            return val_2 + ""+specs.getUnitNames().get(0)+SPECS_SEPARATORS+
                    unitArr[1] + ""+specs.getUnitNames().get(1) +SPECS_SEPARATORS+
                    unitArr[0] + ""+specs.getUnitNames().get(2);
        }
        return "";
    }

    private static int[] getUnitArr(int nums,int unit){
        if(nums == 0){
            return new int[] {0,0};
        }
        if(nums > unit){
            return new int[] {nums % unit,nums / unit};
        }
        return new int[] {nums,0};
    }

    /**
     * 页面提交规格数据 转换 nums 1 * 24 * 10
     * @param specsDto 1箱 1包 1个 1+1*10+1*24*10 = 251
     * @param unitQuantitiesList
     * @return
     *
     * {"id":2,"name":"1箱*6个","unitNames":["箱","个"],"unitVals":[1,6]}
     */
    public static int calGoodNums(SpecsVo specsVo,
                                   List<StockRecordBo.UnitQuantity> unitQuantitiesList
                            ){
        int res = 0;
        int level = specsVo.getUnitNames().size();
        Map<String, Integer> unitMap = unitQuantitiesList.stream()
                .collect(Collectors.toMap(
                        StockRecordBo.UnitQuantity::getName,
                        StockRecordBo.UnitQuantity::getValue
                ));
        if(level == 1){
            return unitMap.get(specsVo.getUnitNames().get(0));
        }
        if(level == 2){
            return unitMap.get(specsVo.getUnitNames().get(0)) *
                    specsVo.getUnitVals().get(1) +
                    unitMap.get(specsVo.getUnitNames().get(1));
        }
        if(level ==3){
            return unitMap.get(specsVo.getUnitNames().get(0)) *
                    specsVo.getUnitVals().get(1) * specsVo.getUnitVals().get(2)+
                    unitMap.get(specsVo.getUnitNames().get(1)) * specsVo.getUnitVals().get(2) +
                    unitMap.get(specsVo.getUnitNames().get(2));
        }
        return res;
    }
}
