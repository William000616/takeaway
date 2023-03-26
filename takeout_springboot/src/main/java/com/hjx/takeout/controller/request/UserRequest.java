package com.hjx.takeout.controller.request;

import lombok.Data;

@Data
public class UserRequest extends BaseRequest {
    private String username;
    private String phone;
    private Integer u_id;
}
