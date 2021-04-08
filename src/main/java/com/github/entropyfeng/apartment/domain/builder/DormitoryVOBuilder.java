package com.github.entropyfeng.apartment.domain.builder;


import com.github.entropyfeng.apartment.domain.DormitoryDirection;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;


public class DormitoryVOBuilder {


    private final DormitoryVO simpleDormitory = new DormitoryVO();
    public DormitoryVOBuilder(String dormitoryName, String buildingName) {

        simpleDormitory.setDormitoryName(dormitoryName);
        simpleDormitory.setBuildingName(buildingName);
    }


    public DormitoryVOBuilder setDescription(String description) {
        simpleDormitory.setDescription(description);
        return this;
    }

    public DormitoryVOBuilder setFloor(Integer floor) {
        simpleDormitory.setFloor(floor);
        return this;
    }

    public DormitoryVOBuilder setTotalCapacity(Integer totalCapacity) {
        simpleDormitory.setTotalCapacity(totalCapacity);
        return this;
    }

    public DormitoryVOBuilder setCurrentCapacity(Integer currentCapacity) {
        simpleDormitory.setCurrentCapacity(currentCapacity);
        return this;
    }

    public DormitoryVOBuilder setInGender(InGender inGender) {
        simpleDormitory.setInGender(inGender.name());
        return this;
    }

    public DormitoryVOBuilder setInGender(String inGender) {
        simpleDormitory.setInGender(inGender);
        return this;
    }


    public DormitoryVOBuilder setDormitoryDirection(DormitoryDirection dormitoryDirection) {
        simpleDormitory.setDormitoryDirection(dormitoryDirection.name());
        return this;
    }

    public DormitoryVOBuilder setDormitoryDirection(String dormitoryDirection) {
        simpleDormitory.setDormitoryDirection(dormitoryDirection);
        return this;
    }

    public DormitoryVO build(){
        return simpleDormitory;
    }

}
