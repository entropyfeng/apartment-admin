package com.github.entropyfeng.apartment.domain.builder;


import com.github.entropyfeng.apartment.domain.DormitoryDirection;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.vo.SimpleDormitoryVO;


public class SimpleDormitoryVOBuilder {


    private final SimpleDormitoryVO simpleDormitory = new SimpleDormitoryVO();
    public SimpleDormitoryVOBuilder(String dormitoryName, String buildingName) {

        simpleDormitory.setDormitoryName(dormitoryName);
        simpleDormitory.setBuildingName(buildingName);
    }


    public SimpleDormitoryVOBuilder setDescription(String description) {
        simpleDormitory.setDescription(description);
        return this;
    }

    public SimpleDormitoryVOBuilder setFloor(Integer floor) {
        simpleDormitory.setFloor(floor);
        return this;
    }

    public SimpleDormitoryVOBuilder setTotalCapacity(Integer totalCapacity) {
        simpleDormitory.setTotalCapacity(totalCapacity);
        return this;
    }

    public SimpleDormitoryVOBuilder setCurrentCapacity(Integer currentCapacity) {
        simpleDormitory.setCurrentCapacity(currentCapacity);
        return this;
    }

    public SimpleDormitoryVOBuilder setInGender(InGender inGender) {
        simpleDormitory.setInGender(inGender.name());
        return this;
    }

    public SimpleDormitoryVOBuilder setInGender(String inGender) {
        simpleDormitory.setInGender(inGender);
        return this;
    }


    public SimpleDormitoryVOBuilder setDormitoryDirection(DormitoryDirection dormitoryDirection) {
        simpleDormitory.setDormitoryDirection(dormitoryDirection.name());
        return this;
    }

    public SimpleDormitoryVOBuilder setDormitoryDirection(String dormitoryDirection) {
        simpleDormitory.setDormitoryDirection(dormitoryDirection);
        return this;
    }

    public SimpleDormitoryVO build(){
        return simpleDormitory;
    }

}
