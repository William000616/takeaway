package com.hjx.takeout.entity;

import lombok.Data;

@Data
public class ShopManager {
    private Integer s_m_id;
    private String name;
    private String phone;
    private String username;
    private String password;
    private Integer r_id;
    private String identity_number;
    private Integer stat;
}
