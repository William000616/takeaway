package com.hjx.takeout.mapper;


import com.hjx.takeout.controller.request.GoodRequest;
import com.hjx.takeout.entity.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodMapper {
    List<Good> list(GoodRequest goodRequest);

    void updateById(Good good);

    void add(Good good);

    void delete(Integer g_id);

    List<Good> listGood(Integer s_id);
}
