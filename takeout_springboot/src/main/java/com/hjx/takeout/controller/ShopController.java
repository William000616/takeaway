package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.controller.request.ShopRequest;
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


}
