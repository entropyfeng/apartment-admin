package com.github.entropyfeng.apartment.domain.po;

import java.io.Serializable;
import java.util.Date;

/**
 * resident
 * @author 
 */
public class Resident implements Serializable {

    private String residentId;

    private String collegeName;

    private String residentName;

    private Date createTime;

    private Date updateTime;


    private static final long serialVersionUID = 1L;


    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}