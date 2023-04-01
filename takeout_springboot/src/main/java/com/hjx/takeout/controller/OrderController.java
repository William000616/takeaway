package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.controller.request.OrderRequest;
import com.hjx.takeout.entity.Order;
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

    @GetMapping("/listAdd")
    public Result listAdd(OrderRequest orderRequest){
        return Result.success(orderService.listAdd(orderRequest));
    }

    @GetMapping("/listNew")
    public Result listNew(OrderRequest orderRequest){
        return Result.success(orderService.listNew(orderRequest));
    }

    @GetMapping("/listById")
    public Result listById(@RequestParam Integer o_id){
        return Result.success(orderService.listById(o_id));
    }

    @PutMapping("/shopPickOrder")
    public Result shopPickOrder(@RequestBody Order order){
        orderService.shopPickOrder(order);
        return Result.success();
    }

    @PutMapping("/changeOrderState")
    public Result changeOrderState(@RequestBody Order order){
        orderService.changeOrderState(order);
        return Result.success();
    }

    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody Order order){
        Order order1= orderService.createOrder(order);
        return Result.success(order1);
    }

//
//    @DeleteMapping("/delete")
//    public Result delete(@RequestParam Integer g_id){
//        goodService.delete(g_id);
//        return Result.success();
//    }

}
