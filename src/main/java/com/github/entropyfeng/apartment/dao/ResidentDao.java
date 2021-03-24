package com.github.entropyfeng.apartment.dao;

import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.po.Resident;
import com.github.entropyfeng.apartment.domain.to.ResidentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ResidentDao {

    List<ResidentInfo> queryResidentInfoByDormitoryId(@Param("dormitoryId")Integer dormitory);
    int deleteByResidentId(@Param("residentId")String residentId);

    int insert(@Param("resident") Resident resident);

    int insertSelective(@Param("resident") Resident resident);

    Resident selectByResidentId(@Param("residentId") String residentId);

    int updateByResidentIdSelective(@Param("resident") Resident resident);

    int updateByResidentId(@Param("resident") Resident resident);

    Gender selectGenderFromResidentId(@Param("residentId") String residentId);
}