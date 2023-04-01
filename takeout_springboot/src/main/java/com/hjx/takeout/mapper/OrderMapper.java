package com.hjx.takeout.mapper;


import com.hjx.takeout.controller.request.OrderRequest;
import com.hjx.takeout.entity.GoodOrder;
import com.hjx.takeout.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> list(OrderRequest orderRequest);

    List<GoodOrder> listById(Integer o_id);

    List<Order> listAdd(OrderRequest orderRequest);

    List<Order> listNew(OrderRequest orderRequest);

    void shopPickOrder(Order order);

    void changeOrderState(Order order);

    Integer createOrder(Order order);

    /*根据订单号查询订单*/
    Order findOrderByOrderNumber(String order_Number);

    Integer addOrderGood(GoodOrder orderGood);

    void addTotalSales(Integer o_id);

    void addGoodSales(Integer count,Integer g_id);
//
//    void updateById(Good good);
//
//    void add(Good good);
//
//    void delete(Integer g_id);

}
