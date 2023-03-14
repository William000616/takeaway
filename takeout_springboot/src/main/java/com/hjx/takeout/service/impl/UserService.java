package com.hjx.takeout.service.impl;

import com.hjx.takeout.entity.User;
import com.hjx.takeout.mapper.UserMapper;
import com.hjx.takeout.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void delete(User user) {
        userMapper.delete(user);
    }
}
