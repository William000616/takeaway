package com.hjx.takeout.controller;

import com.hjx.takeout.common.Result;
import com.hjx.takeout.entity.Address;
import com.hjx.takeout.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @GetMapping("/list")
    public Result list(String uid){
        Integer id = Integer.valueOf(uid);
        List<Address> address=addressService.list(id);
        return Result.success(address);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Address address){
        addressService.add(address);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Address address=addressService.getById(id);
        return Result.success(address);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Address address){
        addressService.update(address);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        addressService.deleteById(id);
        return Result.success();
    }
}
