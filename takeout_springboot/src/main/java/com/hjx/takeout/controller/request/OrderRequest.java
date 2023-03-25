package com.hjx.takeout.controller.request;

import lombok.Data;

@Data
public class OrderRequest extends BaseRequest {
    private String order_Number;
    private Integer u_id;
    private String shop_name;
    private Integer s_id;
    private Integer order_Stat;
}
