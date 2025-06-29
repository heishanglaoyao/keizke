package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.SpecsMapper;
import com.tencent.wxcloudrun.model.SpecsDto;
import com.tencent.wxcloudrun.service.SpecsService;
import org.springframework.stereotype.Service;

@Service
public class SpecsServiceImpl extends ServiceImpl<SpecsMapper, SpecsDto> implements SpecsService {

}
