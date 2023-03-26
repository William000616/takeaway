package com.hjx.takeout.service;

import com.hjx.takeout.entity.Address;

import java.util.List;

public interface IAddressService {
    List<Address> list(Integer u_id);

    void add(Address address);

    List<Address>  getById(Integer a_id);

    void update(Address address);

    void deleteById(Integer a_id);
}
