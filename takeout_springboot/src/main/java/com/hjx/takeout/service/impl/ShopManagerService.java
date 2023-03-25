package com.hjx.takeout.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjx.takeout.controller.dto.LoginDTO;
import com.hjx.takeout.controller.dto.ManagerLoginDTO;
import com.hjx.takeout.controller.dto.ShopRegisterDTO;
import com.hjx.takeout.controller.request.LoginRequest;
import com.hjx.takeout.controller.request.ShopManagerRequest;
import com.hjx.takeout.controller.request.UserRequest;
import com.hjx.takeout.entity.ShopManager;
import com.hjx.takeout.entity.User;
import com.hjx.takeout.exception.ServiceException;
import com.hjx.takeout.mapper.ShopManagerMapper;
import com.hjx.takeout.mapper.UserMapper;
import com.hjx.takeout.service.IShopManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopManagerService implements IShopManagerService {

    @Autowired
    ShopManagerMapper shopManagerMapper;

    @Override
    public Object list(ShopManagerRequest shopManagerRequest) {
        PageHelper.startPage(shopManagerRequest.getPageNum(), shopManagerRequest.getPageSize());
        List<ShopManager> shopManager =shopManagerMapper.list(shopManagerRequest);
        return new PageInfo<>(shopManager);
    }

    @Override
    public void update(ShopManager shopManager) {
        shopManagerMapper.updateById(shopManager);
    }

    @Override
    public Integer add(ShopManager shopManager) {
        shopManagerMapper.add(shopManager);
        return shopManager.getS_m_id();
    }

    @Override
    public void delete(Integer s_m_id) {
        shopManagerMapper.delete(s_m_id);
    }

    @Override
    public ManagerLoginDTO login(LoginRequest request) {
        ShopManager shopManager = shopManagerMapper.login(request);
        if (shopManager==null){
            throw new ServiceException("用户名或密码错误！");
        }
        ManagerLoginDTO managerLoginDTO = new ManagerLoginDTO();
        BeanUtils.copyProperties(shopManager,managerLoginDTO);
        return managerLoginDTO;
    }
}
