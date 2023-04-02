package com.hjx.takeout.service.impl;

import com.hjx.takeout.entity.Comment;
import com.hjx.takeout.mapper.CommentMapper;
import com.hjx.takeout.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public Object list(Integer s_id) {
        List<Comment> comments =commentMapper.list(s_id);
        return comments;
    }

    @Override
    public void add(Comment comment) {
        Date date=new Date();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateformat.format(date);
        comment.setCm_date(strDate);
        commentMapper.add(comment);
    }

    @Override
    public Object listById(Integer o_id) {
        List<Comment> comments =commentMapper.listById(o_id);
        return comments;
    }

//
//    @Override
//    public void add(Category category) {
//        categoryMapper.add(category);
//    }
//
//    @Override
//    public void delete(Integer c_id) {
//        categoryMapper.delete(c_id);
//    }

}
