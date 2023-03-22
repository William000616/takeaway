package com.hjx.takeout.controller.request;

import lombok.Data;

@Data
public class GoodRequest extends BaseRequest {
    private String good_name;
    private Integer s_id;
    private Integer c_id;
}
