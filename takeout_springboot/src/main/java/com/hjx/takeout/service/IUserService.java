package com.hjx.takeout.service;

import com.hjx.takeout.controller.dto.LoginDTO;
import com.hjx.takeout.controller.request.LoginRequest;
import com.hjx.takeout.controller.request.UserRequest;
import com.hjx.takeout.entity.User;

import java.util.List;

public interface IUserService {

    Object list(UserRequest userRequest);

    void update(User user);

    void add(User user);

    void delete(Integer u_id);

    LoginDTO login(LoginRequest request);

    void updatePicSrc(User user);

    Object listAll(UserRequest userRequest);
}
