package com.github.entropyfeng.apartment.dao;

import com.github.entropyfeng.apartment.domain.DormitoryDirection;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.po.ResidentBlock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
                         @Param("targetResident")List<ResidentBlock> targetResident,
                         @Param("description") String description);
    void truncateDormitory();

    Dormitory queryDormitoryByDormitoryName(@Param("dormitoryName") String dormitoryName);

    String selectCampusGroupNameByDormitoryId(@Param("dormitoryId")Integer dormitoryId);
    String selectDormitoryNameByDormitoryId(@Param("dormitoryId")Integer dormitoryId);
    Integer selectCampusGroupIdByDormitoryId(@Param("dormitoryId")Integer dormitoryId);
    String selectBuildingNameByDormitoryId(@Param("dormitoryId")Integer dormitoryId);
    Integer selectBuildingIdByDormitoryId(@Param("dormitoryId")Integer dormitoryId);
    List<Dormitory> queryAllDormitory();

    List<Dormitory> queryBatchDormitory(@Param("list") List<Integer> dormitoryIds);

    Integer queryTotalCapacity(@Param("dormitoryId")Integer dormitoryId);
    int insertSelective(Dormitory record);

    int deleteDormitoryByDormitoryName(@Param("dormitoryName")String dormitoryName);

    int selectDormitoryNum();
    int selectResidentNum();
    int selectBedNum();
    int selectBedNumByGender(@Param("inGender")InGender inGender);
    List<Dormitory> queryDormitoryByBuildingId(@Param("buildingId") Integer buildingId);
    List<Dormitory> queryDormitoryByBuildingName(@Param("buildingName") String buildingName);

    List<Dormitory> queryFilterDormitoryByBuildingName(@Param("buildingName")String buildingName,@Param("inGender") InGender inGender);

    Dormitory queryDormitoryByDormitoryId(@Param("dormitoryId") Integer dormitoryId);

    Dormitory queryDormitoryByResidentId(@Param("residentId")String residentId);

    Long queryVersionByDormitoryId(@Param("dormitoryId") Integer dormitoryId);

    Dormitory filterDormitoryByGender(@Param("inGender") InGender inGender);

    /**
     * 更新整个json模块
     * @param dormitoryId id
     * @param residentBlock {@link ResidentBlock}
     * @return 受影响的行数
     */
    Integer initTargetResident(@Param("dormitoryId")Integer dormitoryId, @Param("residentBlock")ResidentBlock residentBlock);


    Integer checkInTargetResident(@Param("dormitoryId")Integer dormitoryId,@Param("bedId")Integer bedId,@Param("residentId")String residentId );

    Integer checkOutTargetResident(@Param("dormitoryId")Integer dormitoryId,@Param("bedId")Integer bedId);

    Integer queryExistResident(@Param("residentId")String residentId);
}
