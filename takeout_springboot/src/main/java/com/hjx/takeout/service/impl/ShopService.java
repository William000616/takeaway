package com.hjx.takeout.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjx.takeout.controller.request.ShopRequest;
import com.hjx.takeout.entity.Shop;
import com.hjx.takeout.mapper.ShopMapper;
import com.hjx.takeout.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService implements IShopService {

    @Autowired
    ShopMapper shopMapper;

    @Override
    public Object list(ShopRequest shopRequest) {
        PageHelper.startPage(shopRequest.getPageNum(), shopRequest.getPageSize());
        List<Shop> shop =shopMapper.list(shopRequest);
        return new PageInfo<>(shop);
    }

    @Override
    public Object listAdd(ShopRequest shopRequest) {
        PageHelper.startPage(shopRequest.getPageNum(), shopRequest.getPageSize());
        List<Shop> shop =shopMapper.listAdd(shopRequest);
        return new PageInfo<>(shop);
    }

    @Override
    public void update(Shop shop) {
        shopMapper.updateByID(shop);
    }

    @Override
    public Object listById(Integer s_id) {
        List<Shop> shop=shopMapper.listById(s_id);
        return shop;
    }

    @Override
    public void add(Shop shop) {
        shopMapper.add(shop);
    }

    @Override
    public Object listOpen(ShopRequest shopRequest) {
        PageHelper.startPage(shopRequest.getPageNum(), shopRequest.getPageSize());
        List<Shop> shop =shopMapper.listOpen(shopRequest);
        return new PageInfo<>(shop);
    }

}
