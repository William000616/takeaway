package com.hjx.takeout.service;

import com.hjx.takeout.controller.request.GoodRequest;
import com.hjx.takeout.entity.Good;

public interface IGoodService {

    Object list(GoodRequest goodRequest);

    void update(Good good);

    void add(Good good);

    void delete(Integer g_id);

}
