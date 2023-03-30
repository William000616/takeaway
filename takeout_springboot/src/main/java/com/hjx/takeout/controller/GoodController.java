package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.controller.request.GoodRequest;
import com.hjx.takeout.entity.Good;
import com.hjx.takeout.service.impl.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    GoodService goodService;

    @GetMapping("/list")
    public Result list(GoodRequest goodRequest){
        return Result.success(goodService.list(goodRequest));
    }

    @GetMapping("/listGood")
    public Result listGood(@RequestParam Integer s_id){
        return Result.success(goodService.listGood(s_id));
    }


    @PutMapping("/update")
    public Result update(@RequestBody Good good){
        goodService.update(good);
        return Result.success();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Good good){
        goodService.add(good);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer g_id){
        goodService.delete(g_id);
        return Result.success();
    }

}
