package com.github.entropyfeng.apartment.domain.vo;

import com.github.entropyfeng.apartment.domain.po.Building;
import com.github.entropyfeng.apartment.domain.to.CampusAndGroup;

import java.util.Date;
import java.util.Map;

public class BuildingVO {

    private Integer buildingId;

    private String campusName;

    private String campusGroupName;

    private String buildingName;

    private Integer totalFloor;

    private String inGender;

    private Integer maxCapacity;

    private Integer currentCapacity;

    private Integer totalDormitory;

    private String description;

    private Boolean hasElevator;

    private Date createTime;

    private Date updateTime;

    public BuildingVO(Building building, Map<Integer, CampusAndGroup> map) {
        this.buildingId = building.getBuildingId();
        this.campusName = map.get(building.getCampusGroupId()).getCampusName();
        this.campusGroupName = map.get(building.getCampusGroupId()).getCampusGroupName();
        this.buildingName = building.getBuildingName();
        this.totalFloor = building.getTotalFloor();
        this.inGender = building.getInGender().name();
        this.maxCapacity = building.getMaxCapacity();
        this.currentCapacity = building.getCurrentCapacity();
        this.totalDormitory = building.getTotalDormitory();
        this.description = building.getDescription();
        this.hasElevator = building.getHasElevator();
        this.createTime = building.getCreateTime();
        this.updateTime = building.getUpdateTime();
    }


    public BuildingVO() {
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
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

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getInGender() {
        return inGender;
    }

    public void setInGender(String inGender) {
        this.inGender = inGender;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Boolean getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
