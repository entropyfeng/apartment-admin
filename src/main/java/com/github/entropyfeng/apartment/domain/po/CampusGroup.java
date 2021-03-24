package com.github.entropyfeng.apartment.domain.po;

import com.github.entropyfeng.apartment.domain.InGender;

import java.util.Date;

public class CampusGroup {


    private Integer campusGroupId;

    private String campusName;

    private String campusGroupName;

    private String description;

    private InGender inGender;

    private Date createTime;

    private Date updateTime;


    public Integer getCampusGroupId() {
        return campusGroupId;
    }

    public void setCampusGroupId(Integer campusGroupId) {
        this.campusGroupId = campusGroupId;
    }



    public String getCampusGroupName() {
        return campusGroupName;
    }

    public void setCampusGroupName(String campusGroupName) {
        this.campusGroupName = campusGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }


    public InGender getInGender() {
        return inGender;
    }

    public void setInGender(InGender inGender) {
        this.inGender = inGender;
    }
}
