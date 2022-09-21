package com.github.entropyfeng.apartment.domain.po;

import java.util.List;

public class BuildingBlock {
    int buildingId;
    String buildingName;
    List<DormitoryBlock> dormitoryBlocks;

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public List<DormitoryBlock> getDormitoryBlocks() {
        return dormitoryBlocks;
    }

    public void setDormitoryBlocks(List<DormitoryBlock> dormitoryBlocks) {
        this.dormitoryBlocks = dormitoryBlocks;
    }
}
