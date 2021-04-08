package com.github.entropyfeng.apartment.domain.vo;

import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;

import java.util.Date;


public class DormitoryVO  {


    private String dormitoryId;

    private String campusName;

    private String campusGroupName;

    private String buildingName;

    private Integer floor;

    private String dormitoryName;

    private Integer totalCapacity;

    private Integer currentCapacity;

    private String inGender;

    private String dormitoryDirection;

    private String description;

    private Date createTime;

    private Date updateTime;

    public DormitoryVO() {
    }

    public DormitoryVO(Dormitory dormitory,BuildingAndGroup buildingAndGroup) {
        this.dormitoryId = dormitory.getDormitoryId().toString();
        this.campusName = buildingAndGroup.getCampusName();
        this.campusGroupName = buildingAndGroup.getCampusGroupName();
        this.buildingName = buildingAndGroup.getBuildingName();
        this.floor = dormitory.getFloor();
        this.dormitoryName = dormitory.getDormitoryName();
        this.totalCapacity = dormitory.getTotalCapacity();
        this.currentCapacity = dormitory.getCurrentCapacity();
        this.inGender = dormitory.getInGender().name();
        this.dormitoryDirection = dormitory.getDormitoryDirection().name();
        this.description = dormitory.getDescription();
        this.createTime = dormitory.getCreateTime();
        this.updateTime = dormitory.getUpdateTime();
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getCampusGroupName() {
        return campusGroupName;
    }

    public void setCampusGroupName(String campusGroupName) {
        this.campusGroupName = campusGroupName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public Integer getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Integer currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public String getInGender() {
        return inGender;
    }

    public void setInGender(String inGender) {
        this.inGender = inGender;
    }

    public String getDormitoryDirection() {
        return dormitoryDirection;
    }

    public void setDormitoryDirection(String dormitoryDirection) {
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
}
