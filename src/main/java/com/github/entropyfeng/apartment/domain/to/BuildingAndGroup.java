package com.github.entropyfeng.apartment.domain.to;

public class BuildingAndGroup {

    private Integer buildingId;
    private String buildingName;
    private String campusName;
    private String campusGroupName;


    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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
}
