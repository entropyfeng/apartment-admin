package com.github.entropyfeng.apartment.service.impl;


import com.github.entropyfeng.apartment.config.bean.ApartmentScheduleMap;
import com.github.entropyfeng.apartment.dao.ApartmentScheduleDao;
import com.github.entropyfeng.apartment.dao.DormitoryDao;
import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.po.ApartmentSchedule;
import com.github.entropyfeng.apartment.domain.po.BuildingBlock;
import com.github.entropyfeng.apartment.domain.po.CampusGroupBlock;
import com.github.entropyfeng.apartment.domain.po.DormitoryBlock;
import com.github.entropyfeng.apartment.domain.to.ApartmentScheduleCache;
import com.github.entropyfeng.apartment.domain.to.ScheduleTimeInfo;
import com.github.entropyfeng.apartment.domain.vo.DetailApartmentSchedule;
import com.github.entropyfeng.apartment.service.ApartmentScheduleService;
import com.github.entropyfeng.apartment.util.JsonUtil;
import com.github.entropyfeng.apartment.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ApartmentScheduleServiceImpl implements ApartmentScheduleService {

    final StringRedisTemplate redisTemplate;
    final ApartmentScheduleDao scheduleDao;
    final DormitoryDao dormitoryDao;
    final ApartmentScheduleMap apartmentScheduleMap;

    final String SCHEDULE_TIME_START_PREFIX = "SCH_TI_ST_";

    final String SCHEDULE_TIME_END_PREFIX = "SCH_TI_END_";

    private static final Logger logger = LoggerFactory.getLogger(ApartmentScheduleServiceImpl.class);

    @Autowired
    public ApartmentScheduleServiceImpl(StringRedisTemplate redisTemplate, ApartmentScheduleDao scheduleDao, DormitoryDao dormitoryDao, ApartmentScheduleMap apartmentScheduleMap) {
        this.redisTemplate = redisTemplate;
        this.scheduleDao = scheduleDao;
        this.dormitoryDao = dormitoryDao;
        this.apartmentScheduleMap = apartmentScheduleMap;
    }


    @Override
    public void disableSchedule(String scheduleName) {

        //取消了当前任务
        scheduleDao.updateScheduleEnable(scheduleName,false);
    }


    @Override
    public void enableOrDisableSchedule(String scheduleName, boolean enable) {

        ScheduleTimeInfo timeInfo=scheduleDao.queryScheduleTimeInfoByScheduleName(scheduleName);
        if (timeInfo==null){
            return;
        }
        if (enable){
            redisTemplate.opsForValue().getOperations().expire(SCHEDULE_TIME_START_PREFIX+scheduleName,1,TimeUnit.MICROSECONDS);
            scheduleDao.updateScheduleEnable(scheduleName,false);
        }else {
            scheduleDao.updateScheduleEnable(scheduleName,false);
            redisTemplate.opsForValue().getOperations().expire(SCHEDULE_TIME_END_PREFIX+scheduleName,1,TimeUnit.MICROSECONDS);
        }
    }



    @Override
    public void createApartmentSchedule(String scheduleName, String year, Date beginTime, Date endTime, Gender gender) {
        scheduleDao.initApartmentSchedule(scheduleName, year, beginTime, endTime,gender);
    }

    @Override
    public void addStudentIdIntoSchedule(String scheduleName, List<String> studentId) {
        scheduleDao.extendScheduleStudentId(scheduleName, studentId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addDorIntoSchedule(String scheduleName, Integer dormitoryId, List<Integer> bedIds) {

        List<CampusGroupBlock> campusGroupBlocks = handleAddDorIntoSchedule(scheduleName, dormitoryId, bedIds);

        if (campusGroupBlocks == null) {
            return;
        }
        scheduleDao.reconstructTargetApartment(scheduleName, campusGroupBlocks);
        List<String> dorBeds = bedIds.stream().map(str -> dormitoryId + "_" + str).collect(Collectors.toList());
        scheduleDao.extendTargetDorBed(scheduleName, dorBeds);
    }

    public List<CampusGroupBlock> handleAddDorIntoSchedule(String scheduleName, Integer dormitoryId, List<Integer> bedIds) {
        if (bedIds == null || bedIds.isEmpty()) {
            return null;
        }
        ApartmentSchedule apartmentSchedule = scheduleDao.queryApartmentSchedule(scheduleName);

        List<CampusGroupBlock> campusGroupBlocks = apartmentSchedule.getTargetApartment();
        Integer campusGroupId = dormitoryDao.selectCampusGroupIdByDormitoryId(dormitoryId);

        if (campusGroupId == null) {
            logger.warn("not exist dormitoryId ->{}'s campusGroupId", dormitoryId);
            return null;
        }


        boolean existCampusGroup = campusGroupBlocks.stream().anyMatch(t -> t.getCampusGroupId() == campusGroupId);
        if (!existCampusGroup) {
            CampusGroupBlock campusGroupBlock = new CampusGroupBlock();
            String campusGroupName = dormitoryDao.selectCampusGroupNameByDormitoryId(dormitoryId);
            campusGroupBlock.setCampusGroupId(campusGroupId);
            campusGroupBlock.setCampusGroupName(campusGroupName);
            campusGroupBlock.setBuildingBlocks(new ArrayList<>());
            campusGroupBlocks.add(campusGroupBlock);
        }

        CampusGroupBlock campusGroupBlock = campusGroupBlocks.stream().filter(t -> t.getCampusGroupId() == campusGroupId).findAny().get();


        Integer buildingId = dormitoryDao.selectBuildingIdByDormitoryId(dormitoryId);
        boolean existBuilding = campusGroupBlock.getBuildingBlocks().stream().anyMatch(t -> t.getBuildingId() == buildingId);
        if (!existBuilding) {
            BuildingBlock buildingBlock = new BuildingBlock();
            buildingBlock.setBuildingId(buildingId);
            String buildingName = dormitoryDao.selectBuildingNameByDormitoryId(dormitoryId);
            buildingBlock.setBuildingName(buildingName);
            buildingBlock.setDormitoryBlocks(new ArrayList<>());
            campusGroupBlock.getBuildingBlocks().add(buildingBlock);
        }
        BuildingBlock buildingBlock = campusGroupBlock.getBuildingBlocks().stream().filter(t -> t.getBuildingId() == buildingId).findAny().get();
        boolean existDormitory = buildingBlock.getDormitoryBlocks().stream().anyMatch(t -> t.getDormitoryId() == dormitoryId);
        if (!existDormitory) {
            DormitoryBlock dormitoryBlock = new DormitoryBlock();
            dormitoryBlock.setDormitoryId(dormitoryId);
            String dormitoryName = dormitoryDao.selectDormitoryNameByDormitoryId(dormitoryId);
            dormitoryBlock.setDormitoryName(dormitoryName);
            dormitoryBlock.setBedIds(new ArrayList<>());
            buildingBlock.getDormitoryBlocks().add(dormitoryBlock);
        }
        DormitoryBlock dormitoryBlock = buildingBlock.getDormitoryBlocks().stream().filter(t -> t.getDormitoryId() == dormitoryId).findAny().get();

        Set<Integer> set = new HashSet<>();
        set.addAll(dormitoryBlock.getBedIds());
        set.addAll(bedIds);
        dormitoryBlock.setBedIds(new ArrayList<>(set));
        return campusGroupBlocks;
    }

    @Override
    public void addDorIntoSchedule(String scheduleName, Integer dormitoryId) {

        Integer totalCap = dormitoryDao.queryTotalCapacity(dormitoryId);
        if (totalCap == null) {
            logger.warn("dormitory {} not  exist total capacity", totalCap);
            return;
        }
        List<Integer> bedIds = IntStream.range(0, totalCap).boxed().collect(Collectors.toList());
        addDorIntoSchedule(scheduleName, dormitoryId, bedIds);
    }


    @Override
    public void addDorIntoSchedule(String scheduleName, List<Integer> dormitoryId) {


    }

    @Override
    public void addDorIntoSchedule(String scheduleName, List<Integer> dormitoryId, Map<Integer, List<Integer>> dorIdMap) {

        dorIdMap.forEach((k, v) -> {

        });
    }

    @Override
    public void publishLoadSchedule() {

        //只查询enable 为true 状态的计划
        List<ScheduleTimeInfo> timeInfos = scheduleDao.queryAvailableScheduleTimeInfo();

        for (ScheduleTimeInfo timeInfo : timeInfos) {
            Date beginTime = timeInfo.getBeginTime();
            Date endTime = timeInfo.getEndTime();
            String scheduleName = timeInfo.getScheduleName();
            //检查开始时间是否小于结束时间
            if (beginTime.after(endTime)) {
                logger.warn("the begin time {} after end time {} in schedule name {}", beginTime, endTime, scheduleName);
                continue;
            }
            Date curTime = new Date();
            if (beginTime.before(curTime)) {
                redisTemplate.opsForValue().set(SCHEDULE_TIME_START_PREFIX + scheduleName, "", 1, TimeUnit.MICROSECONDS);
            } else {
                //可以优化为在一定时间后过期
                redisTemplate.opsForValue().set(SCHEDULE_TIME_START_PREFIX + scheduleName, "");
                redisTemplate.opsForValue().getOperations().expireAt(SCHEDULE_TIME_START_PREFIX + scheduleName, curTime);
            }
            redisTemplate.opsForValue().set(SCHEDULE_TIME_END_PREFIX + scheduleName, "");
            redisTemplate.opsForValue().getOperations().expireAt(SCHEDULE_TIME_END_PREFIX + scheduleName, endTime);

        }
    }



    @Override
    public void loadScheduleByScheduleName(String scheduleName) {

        ScheduleTimeInfo timeInfo=scheduleDao.queryScheduleTimeInfoByScheduleName(scheduleName);
        Date beginTime = timeInfo.getBeginTime();
        Date endTime=timeInfo.getEndTime();
        Date currentTime = new Date();
        if ((beginTime.before(currentTime) || beginTime.equals(currentTime))&&currentTime.before(endTime)) {

            ApartmentSchedule apartmentSchedule = scheduleDao.queryApartmentSchedule(scheduleName);
            if (apartmentSchedule == null) {
                logger.warn("apartment schedule {} not exist", scheduleName);
                return;
            }
            ApartmentScheduleMap apartmentScheduleMap = (ApartmentScheduleMap) SpringUtil.getBeanByName("apartmentScheduleMap");

            List<String> targetStudentIds = apartmentSchedule.getTargetStudentId();
            Map<String, String> studentMap = targetStudentIds.stream().collect(Collectors.toMap(Function.identity(), s -> scheduleName, (a, b) -> a));
            apartmentScheduleMap.getStudentScheduleMap().putAll(studentMap);

            ApartmentScheduleCache apartmentScheduleCache = new ApartmentScheduleCache();
            List<CampusGroupBlock> campusGroupBlocks = apartmentSchedule.getTargetApartment();
            List<String> campusGroupNameList = campusGroupBlocks.stream().map(CampusGroupBlock::getCampusGroupName).collect(Collectors.toList());

            apartmentScheduleCache.getCampusGroups().addAll(campusGroupNameList);


            //for buildingName
            Map<String, List<String>> campusGroupMap = campusGroupBlocks.stream().collect(Collectors.toMap(CampusGroupBlock::getCampusGroupName, s -> s.getBuildingBlocks().stream().map(BuildingBlock::getBuildingName).collect(Collectors.toList())));
            apartmentScheduleCache.getBuildingMap().putAll(campusGroupMap);


            //for dor scheduleName->buildingName->
            Map<String, List<DormitoryBlock>> dorList = campusGroupBlocks.stream().reduce(new ArrayList<BuildingBlock>(), (a, b) -> {
                a.addAll(b.getBuildingBlocks());
                return a;
            }, (a, b) -> null).stream().collect(Collectors.toMap(BuildingBlock::getBuildingName, s -> new ArrayList<>(s.getDormitoryBlocks())));

            apartmentScheduleCache.getDorMap().putAll(dorList);

            apartmentScheduleMap.getCacheMap().put(scheduleName, apartmentScheduleCache);

        }
    }

    @Override
    public void releaseScheduleByScheduleName(String scheduleName) {

        List<String> studentIdList = apartmentScheduleMap.getCacheMap().get(scheduleName).getStudentIdList();
        apartmentScheduleMap.getCacheMap().remove(scheduleName);
        studentIdList.forEach(id -> apartmentScheduleMap.getStudentScheduleMap().remove(id));

    }


    @Override
    public boolean supportScheduleTimeStart(String key) {

        return key.matches("^" + SCHEDULE_TIME_START_PREFIX);
    }

    @Override
    public boolean supportScheduleTimeEnd(String key) {
        return key.matches("^" + SCHEDULE_TIME_END_PREFIX);
    }

    @Override
    public String acquireMyScheduleName(String studentId) {


        return apartmentScheduleMap.getStudentScheduleMap().get(studentId);

    }

    @Override
    public String reduceTimeStartKeyPrefix(String keyWithPrefix) {
        return keyWithPrefix.substring(SCHEDULE_TIME_START_PREFIX.length());
    }

    @Override
    public String reduceTimeEndKeyPrefix(String keyWithPrefix) {
        return keyWithPrefix.substring(SCHEDULE_TIME_END_PREFIX.length());
    }

    @Override
    public List<String> acquireAvailableCampusGroup(String studentId) {

        String scheduleName = acquireMyScheduleName(studentId);
        return apartmentScheduleMap.getCacheMap().get(scheduleName).getCampusGroups();
    }

    @Override
    @NotNull public List<String> acquireStudentIdList(String scheduleName) {
       String idJsonList= scheduleDao.queryStudentListJson(scheduleName);
       List<String> res=null;
       if (!StringUtils.isEmpty(idJsonList)){
          res= JsonUtil.parseStringListJson(idJsonList);
       }
       if (res==null){
           return new ArrayList<>();
       }else {
           return res;
       }

    }

    @Override
    public List<String> acquireAvailableBuilding(String studentId, String campusGroupName) {
        String scheduleName = acquireMyScheduleName(studentId);
        return apartmentScheduleMap.getCacheMap().get(scheduleName).getBuildingMap().get(campusGroupName);
    }

    @Override
    public @NotNull List<DormitoryBlock> acquireAvailableDorm(String studentId, String buildingName) {

        String scheduleName = acquireMyScheduleName(studentId);
        return apartmentScheduleMap.getCacheMap().get(scheduleName).getDorMap().get(buildingName);
    }

    @Override
    public boolean checkAvailable(String studentId, Integer bedId, Integer dormitoryId, String scheduleName) {

        String res = dormitoryId + "_" + bedId;
        return scheduleDao.queryIsInSchedule(studentId, scheduleName, res);
    }

    @Override
    public List<ApartmentSchedule> acquireAllApartmentSchedule() {

       return scheduleDao.queryAllApartmentSchedule();

    }

    @Override
    public DetailApartmentSchedule acquireDetailApartmentSchedule(String scheduleName) {

       ApartmentSchedule apartmentSchedule= scheduleDao.queryApartmentSchedule(scheduleName);

        return new DetailApartmentSchedule(apartmentSchedule);
    }

    @Override
    public Gender getApartmentScheduleGender(String scheduleName) {

        return scheduleDao.queryTargetScheduleGender(scheduleName);
    }

    @Override
    public List<String> filterStudentIdInSchedule(String scheduleName,List<String> studentIds) {

        scheduleDao.queryTargetScheduleGender(scheduleName);


        return null;
    }
}
