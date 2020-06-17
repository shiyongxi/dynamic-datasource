package com.yx.samples.druid.mybatis.service;

import com.yx.samples.druid.mybatis.entity.User;

import java.util.List;

/**
 * @auther: yx
 * @Date: 2020-06-17 15:52
 * @Description: UserService
 */
public interface UserService {
    void addUser(User user);

    List selectUsersFromDs();

    List selectUserFromDsGroup();
}
