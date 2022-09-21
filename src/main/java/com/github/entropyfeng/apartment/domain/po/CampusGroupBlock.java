package com.github.entropyfeng.apartment.domain.po;

import java.util.List;

public class CampusGroupBlock {
    int campusGroupId;
    String campusGroupName;
    List<BuildingBlock> buildingBlocks;


    public int getCampusGroupId() {
        return campusGroupId;
    }

    public void setCampusGroupId(int campusGroupId) {
        this.campusGroupId = campusGroupId;
    }

    public String getCampusGroupName() {
        return campusGroupName;
    }

    public void setCampusGroupName(String campusGroupName) {
        this.campusGroupName = campusGroupName;
    }

    public List<BuildingBlock> getBuildingBlocks() {
        return buildingBlocks;
    }

    public void setBuildingBlocks(List<BuildingBlock> buildingBlocks) {
        this.buildingBlocks = buildingBlocks;
    }
}
