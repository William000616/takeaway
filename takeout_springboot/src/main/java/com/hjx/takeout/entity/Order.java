package com.hjx.takeout.entity;

import lombok.Data;

@Data
public class Order {
    private Integer o_id;
    private String order_Number;
    private String total_price;
    private Integer u_id;
    private Integer s_id;
    private Integer a_id;
    private String create_time;
    private Integer order_Stat;
    private String good_total_price;
    private String delivery_price;
    private String order_time;
    private String arrive_time;
    private String logo_src;//外
    private String shop_name;//外
    private String address;//外

}
