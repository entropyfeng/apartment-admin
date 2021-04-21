package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.vo.BuildingVO;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface BuildingService {

     void addNewBuilding(BuildingVO buildingVO);

     List<BuildingVO> acquireAllBuildings();

     int acquireBuildingNum();

     List<String> acquireBuildingNames();

     void deleteSingleBuilding(String buildingName);

     Map<Integer, BuildingAndGroup> acquireBuildingAndGroupMap();

     BuildingAndGroup acquireBuildingAndGroupByBuildingID(@NotNull Integer buildingId);
}
