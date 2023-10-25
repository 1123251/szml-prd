package com.yt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2023-10-22 17:43:29
 */
@TableName("product")
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -62406951762415521L;
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    
    private String name;
    
    private String image;
    
    private String description;
    
    private String details;
    
    private Integer type;
    
    private Integer category;
    
    private Integer priceType;
    
    private Integer integralPrice;
    
    private Integer cashPrice;
    
    private Integer exchangeLimit;
    
    private Integer stock;
    /**
     * 上线，下线
     */
    private Integer status;
    
    private Integer caretaker;

    private Integer proxyId;

    private Date startTime;

    private Date endTime;


}

