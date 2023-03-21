package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.controller.request.OrderRequest;
import com.hjx.takeout.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/list")
    public Result list(OrderRequest orderRequest){
        return Result.success(orderService.list(orderRequest));
    }

    @GetMapping("/listById")
    public Result listById(@RequestParam Integer o_id){
        return Result.success(orderService.listById(o_id));
    }

//    @PutMapping("/update")
//    public Result update(@RequestBody Good good){
//        goodService.update(good);
//        return Result.success();
//    }
//
//    @PostMapping("/add")
//    public Result add(@RequestBody Good good){
//        goodService.add(good);
//        return Result.success();
//    }
//
//    @DeleteMapping("/delete")
//    public Result delete(@RequestParam Integer g_id){
//        goodService.delete(g_id);
//        return Result.success();
//    }

}
