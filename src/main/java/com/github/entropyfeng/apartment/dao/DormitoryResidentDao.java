package com.github.entropyfeng.apartment.dao;

import com.github.entropyfeng.apartment.domain.po.DormitoryResident;
import com.github.entropyfeng.apartment.domain.to.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
@Mapper
public interface DormitoryResidentDao {

    void truncateDormitoryResident();

    void insertTemplate(@Param("dormitoryId") Integer dormitoryId,@Param("bedId")Integer bedId );

    void insertBatchTemplate(@Param("dormitoryId")Integer dormitoryId,@Param("list") List<Integer> list);


    List<DormitoryResident> queryAllDormitoryResident();

    DormitoryResident queryDormitoryResidentByResidentId(@Param("residentId")String residentId);

    List<ResidentAndBed> queryDormitoryCurrentInfo(@Param("dormitoryId") Integer dormitoryId);

    List<DormitoryAndResident> queryDormitoryCurrentInfoByResidentName(@Param("residentId") String residentId);

    List<DormitoryAndResident> queryDormitoryCurrentInfoByDormitoryId(@Param("dormitoryId")Integer dormitoryId);


    List<String> queryCurrentBedInfoByResidentName(@Param("residentId") String residentId);

    Integer updateDormitoryResidentRelyVersion(@Param("dormitoryId") Integer dormitoryId, @Param("bedId") Integer bedId, @Param("residentId") String residentId, @Param("version") Long version);

    Integer updateQuitDormitory(@Param("dormitoryId") Integer dormitoryId,  @Param("residentId") String residentId,@Param("version")Long version);

    Integer updateQuitDormitoryRelyDoubleVersion(@Param("dormitoryId") Integer dormitoryId,  @Param("residentId") String residentId,@Param("dormitoryVersion")Long dormitoryVersion,@Param("residentVersion")Long residentVersion);

    Integer updateInDormitoryRelyDoubleVersion(@Param("dormitoryId") Integer dormitoryId,  @Param("bedId")Integer bedId,@Param("residentId") String residentId,@Param("dormitoryVersion")Long dormitoryVersion,@Param("residentVersion")Long residentVersion);

    Integer updateInDorRelyDoubleVersion(@Param("dormitoryId") Integer dormitoryId,  @Param("bedId")Integer bedId,@Param("currentCapacity")Integer currentCapacity,@Param("residentId") String residentId,@Param("dormitoryVersion")Long dormitoryVersion,@Param("residentVersion")Long residentVersion);

    Integer queryResidentCount(@Param("dormitoryId") Integer dormitoryId);

    List<DormitoryIdAndCount> queryBatchResidentCount(@Param("list") List<Integer> dormitoryIdList);

    List<Integer> queryAvailableBed(@Param("dormitoryId") Integer dormitoryId);

    List<String> queryCurrentInPerson(@Param("dormitoryId")Integer dormitoryId);
    Boolean querySingleResidentStatus(@Param("residentId") String residentId);

    ResidentBedVersion queryCurrentBedInfo(@Param("dormitoryId") Integer dormitoryId, @Param("bedId") Integer bedId);

    DormitoryIdAndVersion queryIdAndVersionByResidentName(@Param("residentId")String residentId);

    DormitoryNameAndBedId queryDormitoryNameAndBedId(@Param("residentId")String residentId);
}
