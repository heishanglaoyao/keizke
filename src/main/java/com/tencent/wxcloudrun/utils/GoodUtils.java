package com.tencent.wxcloudrun.utils;

import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.model.bo.StockRecordBo;

import java.util.List;

public class GoodUtils {


    public static final String SPECS_SEPARATORS = " ";

    /**
     * 数量 转换 展示规格信息
     * @param nums
     * @param specsDto
     * @return
     */
    public static String transUnitStr(int nums,SpecsDto specsDto){
        // 1箱 24包 10 个
        int[] unit =  new int[3];
        if(specsDto.getLevel() ==1){
            return nums + ""+specsDto.getUnitName0();
        }
        if(specsDto.getLevel() ==2){
            int[] unitArr = getUnitArr(nums,specsDto.getUnitVal0());
            return unitArr[1] + ""+specsDto.getUnitName1() +SPECS_SEPARATORS+unitArr[0] + ""+specsDto.getUnitName0();
        }
        if(specsDto.getLevel() == 3){
            int[] unitArr = getUnitArr(nums,specsDto.getUnitVal0() * specsDto.getUnitVal1());
            int val_2 = unitArr[1];
            unitArr = getUnitArr(unitArr[0],specsDto.getUnitVal0());
            return val_2 + ""+specsDto.getUnitName2() +SPECS_SEPARATORS+
                    unitArr[1] + ""+specsDto.getUnitName1() +SPECS_SEPARATORS+
                    unitArr[0] + ""+specsDto.getUnitName0();
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
     */
    public static int calGoodNums(SpecsDto specsDto,
                                   List<StockRecordBo.UnitQuantity> unitQuantitiesList
                            ){
        int res = 0;
        if(specsDto.getLevel() >= 1){
            res = unitQuantitiesList.get(0).getValue();
        }
        if(specsDto.getLevel() >= 2){
            //1 包 10个
            res += unitQuantitiesList.get(1).getValue() * specsDto.getUnitVal0();
        }
        if(specsDto.getLevel() >= 3){
            //1 包 10个
            res += unitQuantitiesList.get(2).getValue() * specsDto.getUnitVal0() * specsDto.getUnitVal1();
        }
        return res;
    }
}
