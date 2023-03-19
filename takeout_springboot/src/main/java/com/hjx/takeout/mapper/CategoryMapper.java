package com.hjx.takeout.mapper;

import com.hjx.takeout.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> list(Integer s_id);

    void add(Category category);

    void delete(Integer c_id);

}
