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
    public Result list(@RequestParam Integer u_id){
        List<Address> address=addressService.list(u_id);
        return Result.success(address);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Address address){
        addressService.add(address);
        return Result.success();
    }

    @GetMapping("/listById")
    public Result getById(@RequestParam Integer a_id){
        List<Address> address=addressService.getById(a_id);
        return Result.success(address);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Address address){
        addressService.update(address);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer a_id){
        addressService.deleteById(a_id);
        return Result.success();
    }
}
