package com.hjx.takeout.service;

import com.hjx.takeout.controller.request.OrderRequest;
import com.hjx.takeout.entity.Order;

public interface IOrderService {

    Object list(OrderRequest orderRequest);

    Object listById(Integer o_id);

    Object listAdd(OrderRequest orderRequest);

    Object listNew(OrderRequest orderRequest);

    void shopPickOrder(Order order);

    void changeOrderState(Order order);

    void createOrder(Order order);

//    void update(Good good);
//
//    void add(Good good);
//
//    void delete(Integer g_id);

}
