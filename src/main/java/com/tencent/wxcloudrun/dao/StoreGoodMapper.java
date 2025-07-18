package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.StoreGoodDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreGoodMapper extends BaseMapper<StoreGoodDto> {


    /**
     * 批量更新（每个对象更新不同字段）
     * @param list 更新列表
     * @return 影响行数
     */
    int updateBatchSelective(@Param("list") List<StoreGoodDto> list);

}
