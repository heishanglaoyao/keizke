package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.constant.StockTypeEnum;
import com.tencent.wxcloudrun.dao.StockRecordMapper;
import com.tencent.wxcloudrun.model.GoodDto;
import com.tencent.wxcloudrun.model.GoodStoreDto;
import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.model.StockRecordDto;
import com.tencent.wxcloudrun.model.bo.StockRecordBo;
import com.tencent.wxcloudrun.service.GoodService;
import com.tencent.wxcloudrun.service.GoodStoreService;
import com.tencent.wxcloudrun.service.SpecsService;
import com.tencent.wxcloudrun.service.StockRecordService;
import com.tencent.wxcloudrun.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockRecordServiceImpl extends ServiceImpl<StockRecordMapper, StockRecordDto> implements StockRecordService {

    @Autowired
    GoodService goodService;

    @Autowired
    SpecsService specsService;

    @Autowired
    GoodStoreService goodStoreService;


    @Transactional(rollbackFor = Exception.class)
    public String save(StockRecordBo stockGoodBo){
        String errMsg = null;
        try {
            //报错提交记录
            log.info("save(StockRecordBo ={})", JSONObject.toJSONString(stockGoodBo));
            StockRecordDto dto = ConvertUtils.copyProperties(StockRecordDto.class, stockGoodBo);
            dto.setSelectedGoodJson(JSONObject.toJSONString(stockGoodBo.getSelectedGoods()));
            this.save(dto);

            List<Integer> goodIdList = stockGoodBo.getSelectedGoods().stream().
                    map(g -> g.getId()).collect(Collectors.toList());
            //获取DB商品库存信息
            List<GoodDto> dbGoods = goodService.getBaseMapper().selectBatchIds(goodIdList);

            //查询仓库商品信息
            List<GoodStoreDto> goodStoreList = goodStoreService.getBaseMapper().selectList(
                    Wrappers.<GoodStoreDto>lambdaQuery()
                            .in(GoodStoreDto::getGoodId, goodIdList)
                            .select(GoodStoreDto::getId, GoodStoreDto::getGoodId,GoodStoreDto::getNums,
                                    GoodStoreDto::getStoreId, GoodStoreDto::getPriceInCent
                            )
            );
            List<SpecsDto> dbSpecsList = specsService.getBaseMapper().selectList(null);

            List<GoodStoreDto> updateGoodStores = new ArrayList<>();
            List<GoodStoreDto> addGoodStores = new ArrayList<>();
            for(StockRecordBo.Goods g: stockGoodBo.getSelectedGoods()){
                GoodDto dbGood_ = dbGoods.stream().filter(dbGood -> dbGood.getId().equals(g.getId())).findFirst().get();
                SpecsDto dbSpecs_ = dbSpecsList.stream().filter(dbSpecs -> dbSpecs.getId().equals(dbGood_.getSpecsId())).findFirst().get();
                tansUpdatateGoods(
                        g,
                        dbGood_,
                        StockTypeEnum.getStockTypeEnumByCode(stockGoodBo.getStockType()),
                        dbSpecs_,
                        goodStoreList,
                        stockGoodBo,
                        addGoodStores,
                        updateGoodStores
                );
            }
            //更新商品数量信息
            if(CollectionUtils.isNotEmpty(updateGoodStores)){
                goodStoreService.updateBatchSelective(updateGoodStores);
            }
            if(CollectionUtils.isNotEmpty(addGoodStores)){
                goodStoreService.saveOrUpdateBatch(addGoodStores);
            }
        }catch (Exception e){
            errMsg = e.getMessage();
            log.error("save error", e);
            throw new RuntimeException(errMsg);
        }
        return errMsg;
    }

    /**
     * @param good
     * @param dbGood
     * @param stockTypeEnum
     * @param specsDto
     * @param eidtGoodStoreDto
     * @param stockGoodBo
     * @param addGoodStores
     * @param updateGoodStores
     */
    private void tansUpdatateGoods(StockRecordBo.Goods good,
                                            GoodDto dbGood,
                                            StockTypeEnum stockTypeEnum,
                                                 SpecsDto specsDto,
                                                List<GoodStoreDto> goodStoreList,
                                                 StockRecordBo stockGoodBo,
                                                 List<GoodStoreDto> addGoodStores,
                                                 List<GoodStoreDto> updateGoodStores){
        GoodStoreDto eidtGoodStoreDto = goodStoreList.stream().filter(
                gs -> gs.getGoodId().equals(dbGood.getId())
                        && gs.getStoreId().equals(stockGoodBo.getStoreId())
        ).findFirst().orElse(null);
        //通过规格信息获取商品数量
        int goodCount = calGoodNums(good,specsDto);
        eidtGoodStoreDto = setAddGoodStores(eidtGoodStoreDto,stockGoodBo.getStoreId(),dbGood.getId(),
                addGoodStores,updateGoodStores );
        switch (stockTypeEnum){
            case IN:
                eidtGoodStoreDto.setNums(eidtGoodStoreDto.getNums() + goodCount);
                break;
            case OUT:
                eidtGoodStoreDto.setNums(eidtGoodStoreDto.getNums() - goodCount);
                break;
            case SWITCH:
                eidtGoodStoreDto.setNums(eidtGoodStoreDto.getNums() - goodCount);
                GoodStoreDto eidtGoodStoreDto2 = goodStoreList.stream().filter(
                        gs -> gs.getGoodId().equals(dbGood.getId())
                                && gs.getStoreId().equals(stockGoodBo.getToStoreId())
                ).findFirst().orElse(null);
                eidtGoodStoreDto2 = setAddGoodStores(eidtGoodStoreDto2,stockGoodBo.getToStoreId(),dbGood.getId(),
                        addGoodStores,updateGoodStores );
                eidtGoodStoreDto2.setNums(eidtGoodStoreDto2.getNums() + goodCount);
                break;
            case CHECK:
                eidtGoodStoreDto.setNums(goodCount);
                break;
        }
    }

    private GoodStoreDto setAddGoodStores(GoodStoreDto eidtGoodStoreDto,Integer storeId,Integer goodId,
                    List<GoodStoreDto> addGoodStores,
                    List<GoodStoreDto> updateGoodStores){
        if(eidtGoodStoreDto == null){
            eidtGoodStoreDto = new GoodStoreDto();
            eidtGoodStoreDto.setStoreId(storeId);
            eidtGoodStoreDto.setGoodId(goodId);
            eidtGoodStoreDto.setNums(0);
            eidtGoodStoreDto.setPriceInCent(0l);
            addGoodStores.add(eidtGoodStoreDto);
        }else{
            updateGoodStores.add(eidtGoodStoreDto);
        }
        return eidtGoodStoreDto;
    }

    private int calGoodNums(StockRecordBo.Goods good,SpecsDto specsDto){
        int res = 0;
        if(specsDto.getLevel() >= 1){
            res = good.getUnitQuantitiesList().get(0).getValue();
        }
        if(specsDto.getLevel() >= 2){
            //1 包 10个
            res += good.getUnitQuantitiesList().get(1).getValue() * specsDto.getUnitVal0();
        }
        if(specsDto.getLevel() >= 3){
            //1 包 10个
            res += good.getUnitQuantitiesList().get(2).getValue() * specsDto.getUnitVal0() * specsDto.getUnitVal1();
        }
        return res;
    }



}
