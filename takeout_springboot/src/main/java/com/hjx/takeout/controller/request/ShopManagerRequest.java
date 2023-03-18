package com.hjx.takeout.controller.request;

import lombok.Data;

@Data
public class ShopManagerRequest extends BaseRequest{
    private String username;
    private String phone;
}
