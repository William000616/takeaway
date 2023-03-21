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
//
//    void updateById(Good good);
//
//    void add(Good good);
//
//    void delete(Integer g_id);

}
