package com.hjx.takeout.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjx.takeout.controller.dto.LoginDTO;
import com.hjx.takeout.controller.request.LoginRequest;
import com.hjx.takeout.controller.request.UserRequest;
import com.hjx.takeout.entity.Category;
import com.hjx.takeout.entity.User;
import com.hjx.takeout.exception.ServiceException;
import com.hjx.takeout.mapper.CategoryMapper;
import com.hjx.takeout.mapper.UserMapper;
import com.hjx.takeout.service.ICategoryService;
import com.hjx.takeout.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Object list(Integer s_id) {
        List<Category> categories =categoryMapper.list(s_id);
        return categories;
    }

    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public void delete(Integer c_id) {
        categoryMapper.delete(c_id);
    }

}
