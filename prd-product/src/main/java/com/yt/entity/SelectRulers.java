package com.yt.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SelectRulers {
    private Integer id;
    private String name;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private Integer caretaker ;
    private Integer proxyId ;
    private Integer currentPage;
    private Integer pageSize;
}
