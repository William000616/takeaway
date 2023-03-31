package com.hjx.takeout.entity;

import lombok.Data;

@Data
public class GoodOrder {
    private Integer o_g_id;
    private Integer o_id;
    private Integer g_id;
    private Integer count;
    private String good_name;
    private String price;
    private String good_pic;//外
}
