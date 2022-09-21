package com.github.entropyfeng.apartment.domain.po;

import com.github.entropyfeng.apartment.domain.DormitoryDirection;
import com.github.entropyfeng.apartment.domain.InGender;

import java.util.Date;
import java.util.List;

public class Dormitory {

    private Integer dormitoryId;

    private Integer buildingId;

    private Integer floor;

    private String dormitoryName;

    private Integer totalCapacity;

    private Integer currentCapacity;

    private InGender inGender;

    private DormitoryDirection dormitoryDirection;

    private String description;

    private Long version;

    private Date createTime;

    private Date updateTime;

    private ResidentBlock targetResident;

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public InGender getInGender() {
        return inGender;
    }

    public void setInGender(InGender inGender) {
        this.inGender = inGender;
    }

    public DormitoryDirection getDormitoryDirection() {
        return dormitoryDirection;
    }

    public void setDormitoryDirection(DormitoryDirection dormitoryDirection) {
        this.dormitoryDirection = dormitoryDirection;
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

    public Integer getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Integer currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public ResidentBlock getTargetResident() {
        return targetResident;
    }

    public void setTargetResident(ResidentBlock targetResident) {
        this.targetResident = targetResident;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
