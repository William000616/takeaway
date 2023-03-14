package com.hjx.takeout.entity;

import lombok.Data;

@Data
public class User {
    private Integer u_id;
    private String name;
    private String phone;
    private String username;
    private String password;
    private Integer r_id;
    private Integer score;
    private Integer stat;
    private String picSrc;
}
