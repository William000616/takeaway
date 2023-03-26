package com.hjx.takeout.mapper;


import com.hjx.takeout.controller.request.LoginRequest;
import com.hjx.takeout.controller.request.UserRequest;
import com.hjx.takeout.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list(UserRequest userRequest);

    void updateById(User user);

    void add(User user);

    void delete(Integer u_id);

    User login(LoginRequest request);

    void updatePicSrc(User user);

    List<User> listAll(UserRequest userRequest);
}
