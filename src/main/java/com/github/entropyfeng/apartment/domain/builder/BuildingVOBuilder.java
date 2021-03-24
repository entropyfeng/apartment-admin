package com.github.entropyfeng.apartment.domain.builder;

import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.vo.BuildingVO;

public class BuildingVOBuilder {

    private final BuildingVO buildingVO;

    public BuildingVOBuilder(String buildingName,String campusGroupName) {
        buildingVO=new BuildingVO();
        buildingVO.setBuildingName(buildingName);
        buildingVO.setCampusGroupName(campusGroupName);
    }
    public BuildingVOBuilder setDescription(String description){
        buildingVO.setDescription(description);
        return this;
    }
    public BuildingVOBuilder setTotalFloor(Integer totalFloor){
        buildingVO.setTotalFloor(totalFloor);
        return  this;

    }
    public BuildingVOBuilder setCurrentCapacity(Integer currentCapacity){
        buildingVO.setCurrentCapacity(currentCapacity);
        return  this;
    }
    public BuildingVOBuilder setMaxCapacity(Integer maxCapacity){
        buildingVO.setMaxCapacity(maxCapacity);
        return  this;
    }
    public BuildingVOBuilder setTotalDormitory(Integer totalDormitory){
        buildingVO.setTotalDormitory(totalDormitory);
        return this;
    }
    public BuildingVOBuilder setInGender(InGender inGender){
        buildingVO.setInGender(inGender.name());
        return this;
    }
    public BuildingVOBuilder setInGender(String inGender){
        buildingVO.setInGender(inGender);
        return this;
    }
    public BuildingVOBuilder setHasElevator(Boolean hasElevator){
        buildingVO.setHasElevator(hasElevator);
        return this;
    }
    public BuildingVO build(){
        return buildingVO;
    }
}
