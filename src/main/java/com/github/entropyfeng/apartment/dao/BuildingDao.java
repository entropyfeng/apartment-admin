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


    int deleteBuildingByName(@Param("buildingName")String buildingName);
    Campus queryBuildingByBuildingName(@Param("buildingName")String buildingName);

    List<Building> queryAllBuilding();

    List<BuildingAndGroup> queryRelativeMap();

    BuildingAndGroup querySingleRelativeMap(@Param("buildingId")Integer buildingId);

    Integer queryBuildingIdByBuildingName(@Param("buildingName")String buildingName);

    Boolean queryBuildingExistByBuildingId(@Param("buildingId")Integer buildingId);

    Boolean queryBuildingRelateStatus(@Param("buildingName")String buildingName);

    List<Building> queryAvailableBuilding(@Param("campusGroupName")String campusGroupName,@Param("inGender") InGender inGender);

    List<String> queryBuildingNamesByCampusGroupName(@Param("campusGroupName")String campusGroupName);
    List<String> queryAvailableBuildingName(@Param("campusGroupName")String campusGroupName,@Param("inGender") InGender inGender);

    List<String> queryAllBuildingNames();

    int selectBuildingNum();
}
