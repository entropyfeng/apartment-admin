package com.github.entropyfeng.apartment.dao;

import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.CampusGroup;
import com.github.entropyfeng.apartment.domain.to.CampusAndGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CampusGroupDao {

    int selectCampusGroupCount();
    void insertCampusGroup(@Param("campusGroupId") Integer campusGroupId, @Param("campusName")String campusName, @Param("campusGroupName") String campusGroupName, @Param("inGender")InGender inGender, @Param("campusGroupDescription") String campusGroupDescription);
    void truncateCampusGroup();
    void deleteCampusGroupByCampusGroupName(@Param("campusGroupName")String campusGroupName);
    CampusGroup queryCampusGroupByCampusGroupName(@Param("campusGroupName")String campusGroupName);
    List<CampusGroup> queryAllCampusGroup();
    Integer queryCampusGroupIdByCampusGroupName(@Param("campusGroupName")String campusGroupName);
    List<CampusAndGroup> queryRelativeMap();
    List<CampusGroup> queryAvailableCampusAndGroup(@Param("campusName") String campusName,@Param("inGender")InGender inGender,@Param("mix")InGender mix);
    List<String> queryAvailableCampusAndGroupName(@Param("campusName") String campusName,@Param("inGender")InGender inGender,@Param("mix")InGender mix);
    List<String> queryAllCampusGroupNames();
    List<String> queryCampusGroupNamesByCampusName(@Param("campusName") String campusName);
}
