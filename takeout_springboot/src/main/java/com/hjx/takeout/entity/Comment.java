package com.hjx.takeout.entity;

import lombok.Data;

@Data
public class Comment {
    private Integer u_id;
    private Integer o_id;
    private String cm_date;
    private String message;
    private Integer s_id;
    private Integer cm_id;
    private String username;
}
