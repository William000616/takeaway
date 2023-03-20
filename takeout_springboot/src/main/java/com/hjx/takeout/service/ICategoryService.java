package com.hjx.takeout.service;

import com.hjx.takeout.entity.Category;

public interface ICategoryService {

    Object list(Integer s_id);

    void add(Category user);

    void delete(Integer c_id);
}
