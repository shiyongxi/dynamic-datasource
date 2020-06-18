package com.yx.samples.spel.service.impl;


import com.yx.dynamic.datasource.annotation.DS;
import com.yx.samples.spel.entity.User;
import com.yx.samples.spel.mapper.UserMapper;
import com.yx.samples.spel.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@DS("slave")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @DS("#session.tenantName")
    public List selectSpelBySession() {
        return userMapper.selectUsers();
    }

    @Override
    @DS("#header.tenantName")
    public List selectSpelByHeader() {
        return userMapper.selectUsers();
    }

    @Override
    @DS("#tenantName")
    public List selectSpelByKey(String tenantName) {
        return userMapper.selectUsers();
    }

    @Override
    @DS("#user.tenantName")
    public List selecSpelByTenant(User user) {
        return userMapper.selectUsers();
    }
}
