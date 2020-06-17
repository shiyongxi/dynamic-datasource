package com.yx.samples.druid.mybatis.controller;

import com.yx.samples.druid.mybatis.entity.User;
import com.yx.samples.druid.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-17 16:39
 * @Description: UserController
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(tags = "添加查询用户信息")
public class UserController {
    private final UserService userService;

    @PostMapping("add")
    @ApiOperation("添加用户信息")
    public void add(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("selectUsersFromDs")
    @ApiOperation("查询 slave_1 用户信息")
    public List<User> selectUsersFromDs() {
        return userService.selectUsersFromDs();
    }

    @GetMapping("selectUserFromDsGroup")
    @ApiOperation("查询 slave 用户信息")
    public List<User> selectUserFromDsGroup() {
        return userService.selectUserFromDsGroup();
    }
}
