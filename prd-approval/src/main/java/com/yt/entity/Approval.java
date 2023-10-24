package com.yt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (Approval)实体类
 *
 * @author makejava
 * @since 2023-10-22 17:43:47
 */
@TableName("approval")
@ToString
public class Approval implements Serializable {
    private static final long serialVersionUID = -45470530748329738L;
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;
    
    private Integer draftId;
    /**
     * 审批中，审批驳回，审批通过
     */
    private Integer status;
    
    private Date createTime;
    
    private Date processTime;
    
    private Integer managerId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDraftId() {
        return draftId;
    }

    public void setDraftId(Integer draftId) {
        this.draftId = draftId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

}

