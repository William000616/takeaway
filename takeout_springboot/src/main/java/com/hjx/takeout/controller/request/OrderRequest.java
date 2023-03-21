package com.hjx.takeout.controller.request;

import lombok.Data;

@Data
public class OrderRequest extends BaseRequest {
    private String order_Number;
    private Integer u_id;
}
