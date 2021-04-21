package com.github.entropyfeng.apartment.dao;

import com.github.entropyfeng.apartment.domain.po.Campus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CampusDao {

    void insertCampus(@Param("campusName") String campusName, @Param("campusDescription") String campusDescription);

    void deleteCampus(@Param("campusName") String campusName);
    void truncateCampus();
    Campus queryCampusByCampusName(@Param("campusName")String campusName);
    List<Campus> queryAllCampus();
    List<String> queryAllCampusName();
    int selectCampusCount();
}
