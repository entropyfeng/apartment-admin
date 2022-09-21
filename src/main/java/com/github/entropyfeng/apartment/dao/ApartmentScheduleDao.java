package com.github.entropyfeng.apartment.dao;


import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.po.ApartmentSchedule;
import com.github.entropyfeng.apartment.domain.po.CampusGroupBlock;
import com.github.entropyfeng.apartment.domain.to.ScheduleTimeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ApartmentScheduleDao {


    void truncateApartmentSchedule();

    ApartmentSchedule queryTargetStudentId(@Param("scheduleName")String scheduleName);

    ApartmentSchedule queryTargetApartment(@Param("scheduleName")String scheduleName);

    ApartmentSchedule queryApartmentSchedule(@Param("scheduleName")String scheduleName);

    void updateScheduleEnable(@Param("scheduleName")String scheduleName,@Param("scheduleEnable")Boolean scheduleEnable);
    void extendScheduleStudentId(@Param("scheduleName")String scheduleName,@Param("toExtendIds") List<String> toExtendIds);

    void extendTargetDorBed(@Param("scheduleName")String scheduleName,@Param("toExtendIds") List<String> toExtendIds);
    void reconstructTargetApartment(@Param("scheduleName")String scheduleName, @Param("json")List<CampusGroupBlock> json);
    void insertApartmentSchedule(@Param("scheduleName")String scheduleName, @Param("json") List<String> studentId);

    void initApartmentSchedule(@Param("scheduleName")String scheduleName, @Param("year")String year, @Param("beginTime")Date beginTime, @Param("endTime")Date endTime, @Param("gender")Gender gender);

    void insertTargetApartment(@Param("scheduleName")String scheduleName, @Param("json")List<CampusGroupBlock> campusGroupBlocks);

    List<ApartmentSchedule> queryAllApartmentSchedule();

    List<ApartmentSchedule> queryAllAvailableApartmentSchedule();

    List<ScheduleTimeInfo> queryAvailableScheduleTimeInfo();

    ScheduleTimeInfo queryScheduleTimeInfoByScheduleName(@Param("scheduleName") String scheduleName);

    String queryAvailableStudentSchedule(@Param("studentId") String studentId);

    boolean queryHasDormitory(@Param("studentId")String studentId,@Param("scheduleName") String scheduleName);

    boolean queryIsInSchedule(@Param("studentId")String studentId,@Param("scheduleName") String scheduleName,@Param("dorBed")String dorBed);

    String queryStudentListJson(@Param("scheduleName")String scheduleName);

    Gender queryTargetScheduleGender(@Param("scheduleName")String scheduleName);

    String queryTargetScheduleYear(@Param("scheduleName")String scheduleName);


}
