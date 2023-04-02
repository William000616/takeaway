package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.entity.Comment;
import com.hjx.takeout.service.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/list")
    public Result list(@RequestParam Integer s_id){
        return Result.success(commentService.list(s_id));
    }

    @GetMapping("/listById")
    public Result listById(@RequestParam Integer o_id){
        return Result.success(commentService.listById(o_id));
    }

    @PostMapping("/add")
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return Result.success();
    }
//
//    @DeleteMapping("/delete")
//    public Result delete(@RequestParam Integer c_id){
//        categoryService.delete(c_id);
//        return Result.success();
//    }

}
