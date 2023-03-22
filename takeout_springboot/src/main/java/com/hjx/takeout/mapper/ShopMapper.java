package com.hjx.takeout.mapper;


import com.hjx.takeout.controller.request.ShopRequest;
import com.hjx.takeout.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {
    List<Shop> list(ShopRequest shopRequest);

    List<Shop> listAdd(ShopRequest shopRequest);

    void updateByID(Shop shop);

    List<Shop> listById(Integer s_id);

    void add(Shop shop);
}
