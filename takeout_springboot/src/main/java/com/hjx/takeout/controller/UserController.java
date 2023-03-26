package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.controller.request.LoginRequest;
import com.hjx.takeout.controller.request.UserRequest;
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
    public Result list(UserRequest userRequest){
        return Result.success(userService.list(userRequest));
    }

    @GetMapping("/listAll")
    public Result listAll(UserRequest userRequest){
        return Result.success(userService.listAll(userRequest));
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @PutMapping("/updatePicSrc")
    public Result updatePicSrc(@RequestBody User user){
        userService.updatePicSrc(user);
        return Result.success();
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user){
        userService.add(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        return Result.success(userService.login(request));
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer u_id){
        userService.delete(u_id);
        return Result.success();
    }

}
