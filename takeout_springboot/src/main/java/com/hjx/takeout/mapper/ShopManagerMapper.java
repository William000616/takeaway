package com.hjx.takeout.mapper;


import com.hjx.takeout.controller.request.LoginRequest;
import com.hjx.takeout.controller.request.ShopManagerRequest;
import com.hjx.takeout.entity.ShopManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopManagerMapper {
    List<ShopManager> list(ShopManagerRequest shopManagerRequest);

    void updateById(ShopManager shopManager);

    void add(ShopManager shopManager);

    void delete(Integer s_m_id);

    ShopManager login(LoginRequest request);
}
