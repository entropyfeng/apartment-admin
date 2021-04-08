package com.github.entropyfeng.apartment.config.cache;

import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class CampusCache {

    private final Map<Integer, BuildingAndGroup> buildingIdMap = new ConcurrentHashMap<>();

    private final Map<String,BuildingAndGroup> buildingNameMap=new ConcurrentHashMap<>();

    public void addEntry( BuildingAndGroup buildingAndGroup) {
        buildingIdMap.put(buildingAndGroup.getBuildingId(), buildingAndGroup);
        buildingNameMap.put(buildingAndGroup.getBuildingName(),buildingAndGroup);
    }

    public BuildingAndGroup getBuildingAndGroup(Integer buildingId) {
        return buildingIdMap.get(buildingId);
    }
    public BuildingAndGroup getBuildingAndGroup(String buildingName) {
        return buildingNameMap.get(buildingName);
    }

    public List<Integer> acquireBuildingIdByCampusName(String campusName){

      return   buildingIdMap.values().stream().filter(buildingAndGroup -> buildingAndGroup.getCampusName().equals(campusName)).map(BuildingAndGroup::getBuildingId).collect(Collectors.toList());
    }
    public List<Integer> acquireBuildingIdByCampusGroupName(String campusGroupName){

        return   buildingIdMap.values().stream().filter(buildingAndGroup -> buildingAndGroup.getCampusGroupName().equals(campusGroupName)).map(BuildingAndGroup::getBuildingId).collect(Collectors.toList());
    }
    public Map<Integer, BuildingAndGroup> getBuildingIdMap() {
        return buildingIdMap;
    }
}
