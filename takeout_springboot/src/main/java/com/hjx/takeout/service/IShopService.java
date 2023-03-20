package com.hjx.takeout.service;


import com.hjx.takeout.controller.request.ShopRequest;
import com.hjx.takeout.entity.Shop;

public interface IShopService {

    Object list(ShopRequest shopRequest);

    Object listAdd(ShopRequest shopRequest);

    void update(Shop shop);

    Object listById(Integer s_id);
}
