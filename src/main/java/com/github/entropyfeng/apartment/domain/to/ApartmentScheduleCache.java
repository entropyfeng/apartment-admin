package com.github.entropyfeng.apartment.domain.to;

import com.github.entropyfeng.apartment.domain.po.BuildingBlock;
import com.github.entropyfeng.apartment.domain.po.DormitoryBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApartmentScheduleCache {

    private final List<String> studentIdList=new ArrayList<>();
    private final List<String> campusGroups=new ArrayList<>();
    private final Map<String,List<String>> buildingMap=new HashMap<>();

    //for dor scheduleName->buildingName->
    private final Map<String, List<DormitoryBlock>> dorMap=new HashMap<>();


    public List<String> getCampusGroups() {
        return campusGroups;
    }

    public Map<String, List<String>> getBuildingMap() {
        return buildingMap;
    }

    public Map<String, List<DormitoryBlock>> getDorMap() {
        return dorMap;
    }

    public List<String> getStudentIdList() {
        return studentIdList;
    }
}
