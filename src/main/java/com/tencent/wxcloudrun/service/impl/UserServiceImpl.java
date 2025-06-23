package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.UserMapper;
import com.tencent.wxcloudrun.model.UserDto;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDto> implements UserService {

    @Override
    public List<UserDto> listAllByName(String name) {
        QueryWrapper<UserDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserDto::getName, name);
        return this.list(queryWrapper);
    }
}