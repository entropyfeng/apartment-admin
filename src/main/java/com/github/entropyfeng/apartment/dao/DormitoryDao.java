package com.github.entropyfeng.apartment.dao;

import com.github.entropyfeng.apartment.domain.DormitoryDirection;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Dormitory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface DormitoryDao {


    void insertDormitory(@Param("dormitoryId") Integer dormitoryId,
                         @Param("buildingId") Integer buildingId,
                         @Param("floor") Integer floor,
                         @Param("dormitoryName") String dormitoryName,
                         @Param("totalCapacity") Integer totalCapacity,
                         @Param("inGender") InGender inGender,
                         @Param("dormitoryDirection") DormitoryDirection dormitoryDirection,
                         @Param("description") String description);

    void truncateDormitory();

    Dormitory queryDormitoryByDormitoryName(@Param("dormitoryName") String dormitoryName);

    List<Dormitory> queryAllDormitory();

    int insertSelective(Dormitory record);

    List<Dormitory> queryDormitoryByBuildingId(@Param("buildingId") Integer buildingId);

    List<Dormitory> queryFilterDormitoryByBuildingName(@Param("buildingName")String buildingName,@Param("inGender") InGender inGender);

    Dormitory queryDormitoryByDormitoryId(@Param("dormitoryId") Integer dormitoryId);

    Dormitory queryDormitoryByResidentId(@Param("residentId")String residentId);

    Long queryVersionByDormitoryId(@Param("dormitoryId") Integer dormitoryId);

    Dormitory filterDormitoryByGender(@Param("inGender") InGender inGender);

    Integer updateCurrentCapacityRelyVersion(@Param("dormitoryId") Integer dormitoryId,@Param("currentCapacity")Integer currentCapacity,@Param("version")Long version);

}
