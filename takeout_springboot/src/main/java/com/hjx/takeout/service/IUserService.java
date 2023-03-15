package com.hjx.takeout.service;

import com.hjx.takeout.controller.request.UserRequest;
import com.hjx.takeout.entity.User;

import java.util.List;

public interface IUserService {

    Object list(UserRequest userRequest);

    void update(User user);

    void add(User user);

    void delete(User user);
}
