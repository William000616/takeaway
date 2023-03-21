package com.hjx.takeout.service;

import com.hjx.takeout.controller.request.OrderRequest;

public interface IOrderService {

    Object list(OrderRequest orderRequest);

    Object listById(Integer o_id);

//    void update(Good good);
//
//    void add(Good good);
//
//    void delete(Integer g_id);

}
