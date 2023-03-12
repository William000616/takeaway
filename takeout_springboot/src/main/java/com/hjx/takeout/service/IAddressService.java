package com.hjx.takeout.service;

import com.hjx.takeout.entity.Address;

import java.util.List;

public interface IAddressService {
    List<Address> list(Integer id);

    void add(Address address);

    Address getById(Integer id);

    void update(Address address);

    void deleteById(Integer id);
}
