package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.controller.request.LoginRequest;
import com.hjx.takeout.controller.request.ShopManagerRequest;
import com.hjx.takeout.entity.ShopManager;
import com.hjx.takeout.service.impl.ShopManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ShopManagerController {

    @Autowired
    ShopManagerService shopManagerService;

    @GetMapping("/list")
    public Result list(ShopManagerRequest shopManagerRequest){
        return Result.success(shopManagerService.list(shopManagerRequest));
    }

    @PutMapping("/update")
    public Result update(@RequestBody ShopManager shopManager){
        shopManagerService.update(shopManager);
        return Result.success();
    }

    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody ShopManager shopManager){
        shopManagerService.updatePassword(shopManager);
        return Result.success();
    }

    @PostMapping("/add")
    public Result add(@RequestBody ShopManager shopManager){
        return Result.success(shopManagerService.add(shopManager));
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        return Result.success(shopManagerService.login(request));
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer s_m_id){
        shopManagerService.delete(s_m_id);
        return Result.success();
    }
}
