package com.hjx.takeout.controller.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private Integer u_id;
    private String username;
    private String name;
    private Integer r_id;
    private Integer stat;
    private String picSrc;
}
