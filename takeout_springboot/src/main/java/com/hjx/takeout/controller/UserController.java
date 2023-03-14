package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.entity.Address;
import com.hjx.takeout.entity.User;
import com.hjx.takeout.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public Result list(){
        List<User> user=userService.list();
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user){
        userService.add(user);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody User user){
        userService.delete(user);
        return Result.success();
    }

}
