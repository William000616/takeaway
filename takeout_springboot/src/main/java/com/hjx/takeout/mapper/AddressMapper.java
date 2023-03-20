package com.hjx.takeout.mapper;

import com.hjx.takeout.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressMapper {

    List<Address> list(Integer u_id);

    void add(Address address);

    Address getById(Integer id);

    void updateById(Address address);

    void deleteById(Integer a_id);
}
