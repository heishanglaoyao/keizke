package com.tencent.wxcloudrun.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class GoodRecordVo {

    private Integer id;

    private String name;

    private Integer shopId;

    private Integer storeId;

    private Integer inOutTypeId;

    /**
     * 操作类型 1:出 2:入 3:转 4:盘点
     */
    private Integer opType;

    /**
     *  出/入/转/盘点 日期
     */
    private String opData;

    /**
     * 备注说明
     */
    private String remarks;


    /**
     * 入库商品明细
     */
    private List<GoodRecordDetailVo> detailVos;

}
