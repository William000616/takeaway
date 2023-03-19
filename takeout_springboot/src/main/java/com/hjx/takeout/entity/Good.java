package com.hjx.takeout.entity;

import lombok.Data;

@Data
public class Good {
    private Integer g_id;
    private String good_name;
    private String good_pic;
    private String price;
    private Integer c_id;
    private Integer sales;
    private Integer s_id;
    private String desc;
}
