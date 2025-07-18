package com.tencent.wxcloudrun.model.vo;

import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.model.StoreGoodDto;
import com.tencent.wxcloudrun.utils.ConvertUtils;
import com.tencent.wxcloudrun.utils.GoodUtils;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;



@Data
public class GoodVo {

    private Integer id;

    private String name;

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

    /**
     *  规格["箱","包","个"]
     */
    private List<String> specsUnits;

    private List<StoreGoodDto> storeGoods;

    /**
     * 规格名称 1箱*12包*10个
     */
    private String specsStr;

//    /**
//     * 格式化库存：2箱_12包_1个
//     */
//    private String numStr;


    public static GoodVo trasform(GoodDto dto, SpecsDto specsDto){
        GoodVo vo = ConvertUtils.copyProperties(GoodVo.class,dto);
        if(specsDto != null){
            vo.setSpecsUnits(new ArrayList<>());
            if(!StringUtils.isEmpty(specsDto.getUnitName0())) {
                vo.getSpecsUnits().add(specsDto.getUnitName0());
            }
            if(!StringUtils.isEmpty(specsDto.getUnitName1())) {
                vo.getSpecsUnits().add(specsDto.getUnitName1());
            }
            if(!StringUtils.isEmpty(specsDto.getUnitName2())) {
                vo.getSpecsUnits().add(specsDto.getUnitName2());
            }
            vo.setSpecsStr(specsDto.getName());
//            vo.setNumStr(GoodUtils.transUnitStr(dto.getNums(),specsDto));
        }
        return vo;
    }

}
