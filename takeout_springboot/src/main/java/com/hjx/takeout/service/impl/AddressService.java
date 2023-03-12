package com.hjx.takeout.service.impl;

import com.hjx.takeout.entity.Address;
import com.hjx.takeout.mapper.AddressMapper;
import com.hjx.takeout.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> listAddress() {
        return addressMapper.listAddress();
    }
}
