package com.github.entropyfeng.apartment.dao;

import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Building;
import com.github.entropyfeng.apartment.domain.po.Campus;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BuildingDao {
    void insertBuilding(@Param("buildingId")Integer buildingId, @Param("campusGroupId")Integer campusGroupId, @Param("buildingName")String buildingName, @Param("totalFloor")Integer totalFloor, @Param("maxCapacity")Integer maxCapacity , @Param("currentCapacity")Integer currentCapacity , @Param("totalDormitory")Integer totalDormitory , @Param("inGender")InGender inGender,@Param("description")String description,@Param("hasElevator")Boolean hasElevator);

    void truncateBuilding();

    Campus queryBuildingByBuildingName(@Param("buildingName")String buildingName);

    List<Building> queryAllBuilding();

    List<BuildingAndGroup> queryRelativeMap();

    BuildingAndGroup querySingleRelativeMap(@Param("buildingId")Integer buildingId);

    Integer queryBuildingIdByBuildingName(@Param("buildingName")String buildingName);

    Boolean queryBuildingExistByBuildingId(@Param("buildingId")Integer buildingId);

    List<Building> queryAvailableBuilding(@Param("campusGroupName")String campusGroupName,@Param("inGender") InGender inGender);

    List<String> queryAvailableBuildingName(@Param("campusGroupName")String campusGroupName,@Param("inGender") InGender inGender);

}
