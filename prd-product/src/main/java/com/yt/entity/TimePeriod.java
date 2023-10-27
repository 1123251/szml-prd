package com.yt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TimePeriod {
    @JsonFormat(timezone =  "GMT+8")
    private Date startTime;
    @JsonFormat(timezone =  "GMT+8")
    private Date endTime;
}
