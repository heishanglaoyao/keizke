package com.tencent.wxcloudrun.utils;

import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.model.bo.StockRecordBo;
import com.tencent.wxcloudrun.model.vo.SpecsVo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GoodUtils {


    public static final String SPECS_SEPARATORS = " ";

    /**
     * 数量 转换 展示规格信息
     * @param nums
     * @param specsDto
     * @return
     */
    public static String transUnitStr(int nums, List<String> unitNames,List<Integer> unitVals){
        // 1箱 24包 10 个
        int[] unit =  new int[3];
        int level = unitNames.size();
        if(level ==1){
            return nums + ""+unitNames.get(0);
        }
        if(level ==2){
            int[] unitArr = getUnitArr(nums,unitVals.get(1));
            return unitArr[1] + ""+unitNames.get(0) +SPECS_SEPARATORS+
                    unitArr[0] + ""+unitNames.get(1);
        }
        if(level == 3){
            int[] unitArr = getUnitArr(nums,
                    unitVals.get(1) * unitVals.get(2));
            int val_2 = unitArr[1];
            unitArr = getUnitArr(unitArr[0],unitVals.get(2));
            return val_2 + ""+unitNames.get(0)+SPECS_SEPARATORS+
                    unitArr[1] + ""+unitNames.get(1) +SPECS_SEPARATORS+
                    unitArr[0] + ""+unitNames.get(2);
        }
        return "";
    }

    private static int[] getUnitArr(int nums,int unit){
        if(nums == 0){
            return new int[] {0,0};
        }
        if(nums >= unit){
            return new int[] {nums % unit,nums / unit};
        }
        return new int[] {nums,0};
    }

    /**
     * 计算单位换算后的值
     * @param nums
     * @param unitValArray
     * @return
     */
    public static int calWarningNum(int nums,List<Integer> unitValArray){
        if(nums == 0){
            return 0;
        }
        for(int i=1;i<unitValArray.size();i++){
            nums =nums * unitValArray.get(i);
        }
        return nums ;
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
            return
                    unitMap.get(specsVo.getUnitNames().get(0)) * specsVo.getUnitVals().get(1) * specsVo.getUnitVals().get(2)+
                    unitMap.get(specsVo.getUnitNames().get(1)) * specsVo.getUnitVals().get(2) +
                    unitMap.get(specsVo.getUnitNames().get(2));
        }
        return res;
    }

    public static void parseString(String input, List<Integer> numbers, List<String> units) {
        // 清空列表（避免多次调用时数据累积）
        numbers.clear();
        units.clear();

        // 处理特殊情况：如果输入是纯单位（如 "个"）
        if (!input.contains("*")) {
            // 尝试提取数字（可能没有）
            Pattern pattern = Pattern.compile("^(\\d*)(\\D+)$");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String numStr = matcher.group(1);
                String unit = matcher.group(2);
                numbers.add(numStr.isEmpty() ? 1 : Integer.parseInt(numStr));
                units.add(unit);
            }
            return;
        }

        // 按 '*' 分割字符串
        String[] parts = input.split("\\*");
        Pattern pattern = Pattern.compile("(\\d+)(\\D+)");

        for (String part : parts) {
            Matcher matcher = pattern.matcher(part);
            if (matcher.find()) {
                int num = Integer.parseInt(matcher.group(1));
                String unit = matcher.group(2);
                numbers.add(num);
                units.add(unit);
            }
        }
    }
}
