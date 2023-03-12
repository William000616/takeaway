package com.hjx.takeout.controller;

import com.hjx.takeout.entity.Address;
import com.hjx.takeout.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @GetMapping("/list")
    public List<Address> listAddress(){
        return addressService.listAddress();

    }
}
