package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.GoodRecordOperateEnum;
import com.tencent.wxcloudrun.model.vo.GoodRecordVo;
import org.springframework.stereotype.Service;

@Service
public class GoodRecordService {

    /**
     * 操作
     * @param recordVo
     * @return
     */
    public String operate(GoodRecordVo recordVo) {
        GoodRecordOperateEnum operateEnum = GoodRecordOperateEnum.getByType(recordVo.getOpType());
        String errMsg = null;
        switch (operateEnum) {
            case IN:
                //入库
                errMsg = operateIn(recordVo);
                break;
            case OUT:
                //出库
                errMsg = operateOut(recordVo);
                break;
            case TRANS:
                //转仓
                errMsg = operateTrans(recordVo);
                break;
            case CROSS:
                //跨店
                errMsg = operateCross(recordVo);
                break;
            default:
                errMsg = "操作类型错误";
        };
        return errMsg;
    }


    private String operateIn(GoodRecordVo recordVo){


        return null;
    }

    private String operateOut(GoodRecordVo recordVo){
        return null;
    }

    private String operateTrans(GoodRecordVo recordVo){
        return null;
    }

    private String operateCross(GoodRecordVo recordVo){
        return null;
    }
}
