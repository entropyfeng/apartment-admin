package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.po.ApartmentSchedule;
import com.github.entropyfeng.apartment.domain.po.DormitoryBlock;
import com.github.entropyfeng.apartment.domain.vo.DetailApartmentSchedule;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ApartmentScheduleService {

    void disableSchedule(String scheduleName);

    void enableOrDisableSchedule(String scheduleName,boolean enable);

    void createApartmentSchedule(String scheduleName, String year, Date beginTime, Date endTime, Gender gender);

    void addStudentIdIntoSchedule(String scheduleName,List<String> studentId);

    void addDorIntoSchedule(String scheduleName,Integer dormitoryId,List<Integer> bedIds);

    void addDorIntoSchedule(String scheduleName,Integer dormitoryId);

    void addDorIntoSchedule(String scheduleName,List<Integer> dormitoryId);

    void addDorIntoSchedule(String scheduleName, List<Integer> dormitoryId, Map<Integer,List<Integer>> dorIdMap);

    void publishLoadSchedule();

    void loadScheduleByScheduleName(String scheduleName);

    void releaseScheduleByScheduleName(String scheduleName);

    boolean supportScheduleTimeStart(String key);

    boolean supportScheduleTimeEnd(String key);

    String acquireMyScheduleName(String studentId);

    String reduceTimeStartKeyPrefix(String keyWithPrefix);

    String reduceTimeEndKeyPrefix(String keyWithPrefix);
    List<String> acquireAvailableCampusGroup(String studentId);

    @NotNull List<String>
    acquireStudentIdList(String scheduleName);
    List<String> acquireAvailableBuilding(String studentId,String campusGroupName);

    @NotNull List<DormitoryBlock> acquireAvailableDorm(String studentId, String buildingName);

    boolean checkAvailable(String studentId,Integer bedId,Integer dormitoryId,String scheduleName);

    List<ApartmentSchedule> acquireAllApartmentSchedule();

    DetailApartmentSchedule acquireDetailApartmentSchedule(String scheduleName);

    Gender getApartmentScheduleGender(String scheduleName);

    List<String> filterStudentIdInSchedule(String scheduleName,List<String> studentIds);
}
