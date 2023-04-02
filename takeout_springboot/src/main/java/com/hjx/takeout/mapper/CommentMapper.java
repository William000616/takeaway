package com.hjx.takeout.mapper;

import com.hjx.takeout.entity.Category;
import com.hjx.takeout.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> list(Integer s_id);

    void add(Comment comment);

    List<Comment> listById(Integer o_id);
//
//    void add(Category category);
//
//    void delete(Integer c_id);

}
