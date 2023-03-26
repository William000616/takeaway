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
    public List<Address> list(Integer u_id) {
        return addressMapper.list(u_id);
    }

    @Override
    public void add(Address address) {
        addressMapper.add(address);
    }

    @Override
    public List<Address>  getById(Integer a_id) {
        return addressMapper.getById(a_id);
    }

    @Override
    public void update(Address address) {
        addressMapper.updateById(address);
    }

    @Override
    public void deleteById(Integer a_id) {
        addressMapper.deleteById(a_id);
    }


}
