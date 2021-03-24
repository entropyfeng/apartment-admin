package com.github.entropyfeng.apartment.domain.vo;

import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.to.ResidentInfo;

import java.util.List;


public class DormitoryVO  extends SimpleDormitoryVO{

    private List<ResidentInfo> residentInfos;

   public DormitoryVO(){
        super();
    }

   public DormitoryVO(Dormitory dormitory, BuildingAndGroup buildingAndGroup, List<ResidentInfo> residentInfos){
        super(dormitory,buildingAndGroup);
        this.residentInfos=residentInfos;
    }



}
