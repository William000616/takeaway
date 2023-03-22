package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.controller.request.ShopRequest;
import com.hjx.takeout.entity.Good;
import com.hjx.takeout.entity.Shop;
import com.hjx.takeout.service.impl.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping("/list")
    public Result list(ShopRequest shopRequest){
        return Result.success(shopService.list(shopRequest));
    }

    @GetMapping("/listById")
    public Result listById(@RequestParam Integer s_id){
        return Result.success(shopService.listById(s_id));
    }

    @GetMapping("/listAdd")
    public Result listAdd(ShopRequest shopRequest){
        return Result.success(shopService.listAdd(shopRequest));
    }

    @PutMapping("/update")
    public Result update(@RequestBody Shop shop){
        shopService.update(shop);
        return Result.success();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Shop shop){
        shopService.add(shop);
        return Result.success();
    }
}
