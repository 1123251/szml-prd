package com.yt.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TimePeriod {
    private Date startTime;
    private Date endTime;
}
