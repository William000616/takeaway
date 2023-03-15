package com.hjx.takeout.mapper;


import com.hjx.takeout.controller.request.UserRequest;
import com.hjx.takeout.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> list(UserRequest userRequest);

    void updateById(User user);

    void add(User user);

    void delete(User user);
}
