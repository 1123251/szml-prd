package com.yt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2023-10-22 17:43:29
 */
@TableName("product")
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Integer getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(Integer integralPrice) {
        this.integralPrice = integralPrice;
    }

    public Integer getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(Integer cashPrice) {
        this.cashPrice = cashPrice;
    }

    public Integer getExchangeLimit() {
        return exchangeLimit;
    }

    public void setExchangeLimit(Integer exchangeLimit) {
        this.exchangeLimit = exchangeLimit;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Integer caretaker) {
        this.caretaker = caretaker;
    }

}

