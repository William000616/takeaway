package com.hjx.takeout.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjx.takeout.controller.request.GoodRequest;
import com.hjx.takeout.entity.Good;
import com.hjx.takeout.exception.ServiceException;
import com.hjx.takeout.mapper.GoodMapper;
import com.hjx.takeout.service.IGoodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService implements IGoodService {

    @Autowired
    GoodMapper goodMapper;

    @Override
    public Object list(GoodRequest goodRequest) {
        PageHelper.startPage(goodRequest.getPageNum(), goodRequest.getPageSize());
        List<Good> goods =goodMapper.list(goodRequest);
        return new PageInfo<>(goods);
    }

    @Override
    public void update(Good good) {
        goodMapper.updateById(good);
    }

    @Override
    public void add(Good good) {
        goodMapper.add(good);
    }

    @Override
    public void delete(Integer g_id) {
        goodMapper.delete(g_id);
    }


}
