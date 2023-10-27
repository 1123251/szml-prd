package com.yt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DateRecordsVO {
    @JsonFormat(timezone = "GMT+8")
    private Date time;
    private int nums;
}
