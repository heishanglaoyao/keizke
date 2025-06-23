package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.model.UserDto;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public boolean add(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody UserDto user) {
        return userService.updateById(user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.removeById(id);
    }

    // 查询所有
    @GetMapping("/list")
    public List<UserDto> list() {
        return userService.list();
    }

    // 条件查询
    @GetMapping("/query")
    public List<UserDto> query(String name) {
        QueryWrapper<UserDto> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return userService.list(queryWrapper);
    }

    // 分页查询
    @GetMapping("/page")
    public Page<UserDto> page(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<UserDto> page = new Page<>(pageNum, pageSize);
        return userService.page(page);
    }
}