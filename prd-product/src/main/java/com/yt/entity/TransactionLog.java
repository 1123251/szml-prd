package com.yt.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (TransactionLog)实体类
 *
 * @author makejava
 * @since 2023-10-22 18:33:44
 */
@TableName("transaction_log")
public class TransactionLog implements Serializable {
    private static final long serialVersionUID = 603689430488763386L;
    /**
     * 事务ID
     */
    private String id;
    /**
     * 业务标识
     */
    private String business;
    /**
     * 对应业务表中的主键
     */
    private String foreignKey;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

}

