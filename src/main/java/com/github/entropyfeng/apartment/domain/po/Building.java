package com.github.entropyfeng.apartment.domain.po;


import com.github.entropyfeng.apartment.domain.InGender;

import java.util.Date;

public class Building {

    private Integer buildingId;

    private Integer campusGroupId;

    private String buildingName;

    private Integer totalFloor;

    private InGender inGender;

    private Integer maxCapacity;

    private Integer currentCapacity;

    private Integer totalDormitory;

    private String description;

    private Boolean hasElevator;
    private Date createTime;
    private Date updateTime;



    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getCampusGroupId() {
        return campusGroupId;
    }

    public void setCampusGroupId(Integer campusGroupId) {
        this.campusGroupId = campusGroupId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public InGender getInGender() {
        return inGender;
    }

    public void setInGender(InGender inGender) {
        this.inGender = inGender;
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

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Integer currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Integer getTotalDormitory() {
        return totalDormitory;
    }

    public void setTotalDormitory(Integer totalDormitory) {
        this.totalDormitory = totalDormitory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
    }
}
