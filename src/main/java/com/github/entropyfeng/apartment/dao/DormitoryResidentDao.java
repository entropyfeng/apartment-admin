package com.github.entropyfeng.apartment.dao;

import com.github.entropyfeng.apartment.domain.po.DormitoryResident;
import com.github.entropyfeng.apartment.domain.to.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DormitoryResidentDao {

    void truncateDormitoryResident();

    void insertTemplate(@Param("dormitoryId") Integer dormitoryId,@Param("bedId")Integer bedId );

    void insertBatchTemplate(@Param("dormitoryId")Integer dormitoryId,@Param("list") List<Integer> list);


    List<DormitoryResident> queryAllDormitoryResident();

    List<ResidentAndBed> queryDormitoryCurrentInfo(@Param("dormitoryId") Integer dormitoryId);

    List<DormitoryAndResident> queryDormitoryCurrentInfoByResidentName(@Param("residentName") String residentName);

    List<DormitoryAndResident> queryDormitoryCurrentInfoByDormitoryId(@Param("dormitoryId")Integer dormitoryId);


    List<String> queryCurrentBedInfoByResidentName(@Param("residentName") String residentName);

    Integer updateDormitoryResidentRelyVersion(@Param("dormitoryId") Integer dormitoryId, @Param("bedId") Integer bedId, @Param("residentName") String residentName, @Param("version") Long version);

    Integer updateQuitDormitory(@Param("dormitoryId") Integer dormitoryId,  @Param("residentName") String residentName,@Param("version")Long version);

    Integer queryResidentCount(@Param("dormitoryId") Integer dormitoryId);

    List<DormitoryIdAndCount> queryBatchResidentCount(@Param("list") List<Integer> dormitoryIdList);

    List<Integer> queryAvailableBed(@Param("dormitoryId") Integer dormitoryId);

    List<String> queryCurrentInPerson(@Param("dormitoryId")Integer dormitoryId);
    Boolean querySingleResidentStatus(@Param("residentName") String residentName);

    ResidentBedVersion queryCurrentBedInfo(@Param("dormitoryId") Integer dormitoryId, @Param("bedId") Integer bedId);

    DormitoryIdAndVersion queryIdAndVersionByResidentName(@Param("residentName")String residentName);

}
