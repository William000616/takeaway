package com.hjx.takeout.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjx.takeout.controller.request.OrderRequest;
import com.hjx.takeout.entity.GoodOrder;
import com.hjx.takeout.entity.Order;
import com.hjx.takeout.mapper.OrderMapper;
import com.hjx.takeout.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderMapper orderMapper;


    @Override
    public Object list(OrderRequest orderRequest) {
        PageHelper.startPage(orderRequest.getPageNum(), orderRequest.getPageSize());
        List<Order> orders =orderMapper.list(orderRequest);
        return new PageInfo<>(orders);
    }

    @Override
    public Object listById(Integer o_id) {
        List<GoodOrder> goodOrders =orderMapper.listById(o_id);
        return new PageInfo<>(goodOrders);
    }

    @Override
    public Object listAdd(OrderRequest orderRequest) {
        PageHelper.startPage(orderRequest.getPageNum(), orderRequest.getPageSize());
        List<Order> orders =orderMapper.listAdd(orderRequest);
        return new PageInfo<>(orders);
    }

    @Override
    public Object listNew(OrderRequest orderRequest) {
        PageHelper.startPage(orderRequest.getPageNum(), orderRequest.getPageSize());
        List<Order> orders =orderMapper.listNew(orderRequest);
        return new PageInfo<>(orders);
    }

    @Override
    public void shopPickOrder(Order order) {
        Date date=new Date();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateformat.format(date);
        order.setOrder_time(strDate);
        orderMapper.shopPickOrder(order);
    }

    @Override
    public void changeOrderState(Order order) {
        Date date=new Date();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateformat.format(date);
        if(order.getOrder_Stat()==4){
            order.setArrive_time(strDate);
        }
        orderMapper.changeOrderState(order);
    }

    @Override
    public void createOrder(Order order) {
        Date date=new Date();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String orderNumber = "DM" +dateUtil +"000" + order.getU_id();
        String strDate = dateformat.format(date);
        order.setCreate_time(strDate);
        order.setOrder_Number(strDate.toString());
        orderMapper.createOrder(order);
    }

//    @Override
//    public void update(Good good) {
//        goodMapper.updateById(good);
//    }
//
//    @Override
//    public void add(Good good) {
//        goodMapper.add(good);
//    }
//
//    @Override
//    public void delete(Integer g_id) {
//        goodMapper.delete(g_id);
//    }

}
