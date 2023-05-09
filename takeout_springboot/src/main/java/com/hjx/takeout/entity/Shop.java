package com.hjx.takeout.entity;

import lombok.Data;

@Data
public class Shop {
    private Integer s_id;
    private String logo_src;
    private String shop_name;
    private Integer s_m_id;
    private String license;
    private Integer stat;
    private String delivery_cost;
    private String min_cost;
    private String total_sales;
    private String shop_phone;
    private String address_detail;
    private String delivery_time;
    private String desc;
    private String name;//外
    private String shop_pic;
    private String shop_env_pic;
    private String license_pic;

}
