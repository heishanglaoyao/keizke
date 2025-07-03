package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@TableName("t_good_record_detail")
public class GoodRecorDetailDto extends BaseDto {

    /*
    批次记录id
     */
    @TableField("batch_record_id")
    private Integer batchRecordId;

    private Integer num;

}
