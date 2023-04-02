package com.hjx.takeout.service;

import com.hjx.takeout.entity.Category;
import com.hjx.takeout.entity.Comment;

public interface ICommentService {
    Object list(Integer s_id);

    void add(Comment comment);

    Object listById(Integer o_id);
}
