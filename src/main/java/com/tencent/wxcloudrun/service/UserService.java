package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.UserDto;

import java.util.List;

public interface UserService extends IService<UserDto> {
    // 自定义方法
    List<UserDto> listAllByName(String name);
}