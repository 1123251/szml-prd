package com.yt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ExchangeRecords)实体类
 *
 * @author makejava
 * @since 2023-10-26 10:03:52
 */
@Data
@TableName("exchange_records")
public class ExchangeRecords implements Serializable {
    private static final long serialVersionUID = 732700535600761709L;
    /**
     * id
     */
    @TableId(value="id",type = IdType.AUTO)
    private Long id;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 价格类型（0积分，1现金，2积分加现金）
     */
    private Integer priceType;
    
    private Integer nums;
    
    private Date time;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}

