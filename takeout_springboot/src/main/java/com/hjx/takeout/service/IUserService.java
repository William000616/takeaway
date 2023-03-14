package com.hjx.takeout.service;

import com.hjx.takeout.entity.User;

import java.util.List;

public interface IUserService {

    List<User> list();

    void update(User user);

    void add(User user);

    void delete(User user);
}
