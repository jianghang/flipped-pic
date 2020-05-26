package com.github.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.example.dao.UserMapper;
import com.github.example.entity.User;
import com.github.example.service.IExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleServiceImpl implements IExampleService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().eq("name", "Jack"));
        userMapper.selectUserByName("Jack");
        userMapper.updateById(userList.get(0));
        userMapper.updateById(userList.get(0));
        return userList;
    }
}
