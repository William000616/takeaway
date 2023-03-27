package com.hjx.takeout.service;

import com.hjx.takeout.controller.dto.ManagerLoginDTO;
import com.hjx.takeout.controller.dto.ShopRegisterDTO;
import com.hjx.takeout.controller.request.LoginRequest;
import com.hjx.takeout.controller.request.ShopManagerRequest;
import com.hjx.takeout.entity.ShopManager;

public interface IShopManagerService {

    Object list(ShopManagerRequest shopManagerRequest);

    void update(ShopManager shopManager);

    Integer add(ShopManager shopManager);

    void delete(Integer s_m_id);

    ManagerLoginDTO login(LoginRequest request);

    void updatePassword(ShopManager shopManager);
}
