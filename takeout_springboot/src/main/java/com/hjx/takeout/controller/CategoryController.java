package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.entity.Category;
import com.hjx.takeout.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public Result list(@RequestParam Integer s_id){
        return Result.success(categoryService.list(s_id));
    }


    @PostMapping("/add")
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer c_id){
        categoryService.delete(c_id);
        return Result.success();
    }

}
