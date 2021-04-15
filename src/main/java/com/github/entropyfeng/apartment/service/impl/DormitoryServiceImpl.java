package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.config.cache.CampusCache;
import com.github.entropyfeng.apartment.dao.BuildingDao;
import com.github.entropyfeng.apartment.dao.DormitoryDao;
import com.github.entropyfeng.apartment.dao.DormitoryResidentDao;
import com.github.entropyfeng.apartment.dao.StudentDao;
import com.github.entropyfeng.apartment.domain.DormitoryDirection;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.to.DormitoryAndResident;
import com.github.entropyfeng.apartment.domain.to.StudentResident;
import com.github.entropyfeng.apartment.domain.to.StudentTo;
import com.github.entropyfeng.apartment.domain.vo.DetailDormitory;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.service.ApartmentIdService;
import com.github.entropyfeng.apartment.service.BuildingService;
import com.github.entropyfeng.apartment.service.DormitoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DormitoryServiceImpl implements DormitoryService {


    private static final Logger logger = LoggerFactory.getLogger(DormitoryServiceImpl.class);


    @Autowired
    public DormitoryServiceImpl(StudentDao studentDao, BuildingService buildingService, CampusCache campusCache, ApartmentIdService idService, DormitoryResidentDao dormitoryResidentDao, DormitoryDao dormitoryDao, BuildingDao buildingDao) {
        this.dormitoryDao = dormitoryDao;
        this.buildingDao = buildingDao;
        this.idService = idService;
        this.dormitoryResidentDao = dormitoryResidentDao;
        this.buildingService = buildingService;
        this.campusCache = campusCache;
        this.studentDao = studentDao;
    }

    private final StudentDao studentDao;

    private final ApartmentIdService idService;

    private final CampusCache campusCache;

    private final DormitoryDao dormitoryDao;

    private final BuildingDao buildingDao;

    private final DormitoryResidentDao dormitoryResidentDao;

    private final BuildingService buildingService;

    @Override
    public void addNewDormitory(DormitoryVO dormitoryVO) {
        if (StringUtils.isEmpty(dormitoryVO.getDormitoryName())) {
            String err = "lack of dormitoryName";
            logger.error(err);
            throw new IllegalArgumentException(err);
        }

        if (!StringUtils.isEmpty(dormitoryVO.getBuildingName())) {
            addNewDormitoryByBuildingName(dormitoryVO);
        } else {
            String error = "lack of relative buildingInfo";
            logger.error(error);
            throw new IllegalArgumentException(error);
        }

    }


    private void addNewDormitoryByBuildingName(DormitoryVO dormitoryVO) {

        String buildingName = dormitoryVO.getBuildingName();
        Integer buildingId = buildingDao.queryBuildingIdByBuildingName(buildingName);
        if (buildingId != null) {
            Dormitory dormitory = new Dormitory();
            dormitory.setBuildingId(buildingId);
            dormitory.setFloor(dormitoryVO.getFloor());
            dormitory.setDormitoryId(idService.getNextDormitoryId());
            dormitory.setDormitoryName(dormitoryVO.getDormitoryName());
            dormitory.setInGender(InGender.toInGender(dormitoryVO.getInGender()));
            dormitory.setDormitoryDirection(DormitoryDirection.toDormitoryDirection(dormitoryVO.getDormitoryDirection()));
            dormitory.setTotalCapacity(dormitoryVO.getTotalCapacity());
            dormitory.setDescription(dormitoryVO.getDescription());

            dormitoryDao.insertSelective(dormitory);
            return;
        }
        String errorInfo = "not exists building";
        logger.error(errorInfo);
        throw new IllegalArgumentException(errorInfo);

    }


    @Override
    public List<DormitoryVO> queryAllDormitories() {
        List<Dormitory> dormitoryList = dormitoryDao.queryAllDormitory();
        return postHandlerDormitoryList(dormitoryList);
    }

    private DetailDormitory acquireDetailDormitory(@NotNull Dormitory dormitory) {
        BuildingAndGroup buildingAndGroup = campusCache.getBuildingAndGroup(dormitory.getBuildingId());
        List<DormitoryAndResident> dormitoryAndResidents = dormitoryResidentDao.queryDormitoryCurrentInfoByDormitoryId(dormitory.getDormitoryId());
        HashMap<String, Integer> bedIdMap = new HashMap<>();
        dormitoryAndResidents.stream().filter(Objects::nonNull).forEach(temp -> bedIdMap.put(temp.getResidentId(), temp.getBedId()));
        List<String> studentIdList = dormitoryAndResidents.stream().filter(Objects::nonNull).map(DormitoryAndResident::getResidentId).collect(Collectors.toList());
        List<StudentResident> studentResidents = new ArrayList<>();

        Set<Integer> bedIds=new HashSet<>();
        if (!studentIdList.isEmpty()) {
            List<StudentTo> students = studentDao.queryStudentToByStudentIds(studentIdList);
            studentResidents = students.stream().map(student -> new StudentResident(student, bedIdMap.get(student.getStudentId()))).collect(Collectors.toList());
            bedIds= studentResidents.stream().map(StudentResident::getBedId).collect(Collectors.toSet());

        }
        List<Integer> lackIds=new ArrayList<>();
        for (int i = 0; i < dormitory.getTotalCapacity(); i++) {
            if (!bedIds.contains(i)){
                studentResidents.add(new StudentResident(i));
            }
        }

        return new DetailDormitory(dormitory, buildingAndGroup, studentResidents);
    }

    @Override
    public DetailDormitory queryMyDormitory(String username) {


        Dormitory dormitory = dormitoryDao.queryDormitoryByResidentId(username);
        if(dormitory==null){
            return null;
        }
        return acquireDetailDormitory(dormitory);
    }

    @Override
    public DetailDormitory queryDetailDormitory(Integer dormitoryId,List<String> roleList) {

        Dormitory dormitory = dormitoryDao.queryDormitoryByDormitoryId(dormitoryId);
        DetailDormitory detailDormitory=  acquireDetailDormitory(dormitory);
        if (!roleList.contains("admin")){

            detailDormitory.getStudentList().forEach(studentResident -> {
                studentResident.setIdCardNumber("*");
                studentResident.setEmail("*");
                studentResident.setPhone("*");
            });

        }
        return detailDormitory;
    }

    @Override
    public List<DormitoryVO> queryDormitory(DormitoryVO dormitoryVO) {
        String dormitoryName = dormitoryVO.getDormitoryName();

        InGender inGender = null;
        if (dormitoryVO.getInGender() != null) {
            inGender = InGender.valueOf(dormitoryVO.getInGender());
        }
        DormitoryDirection direction = null;
        if (dormitoryVO.getDormitoryDirection() != null) {
            direction = DormitoryDirection.valueOf(dormitoryVO.getDormitoryDirection());
        }
        String campusGroupName = dormitoryVO.getCampusGroupName();
        String campusName = dormitoryVO.getCampusName();
        String buildingName = dormitoryVO.getBuildingName();
        BuildingAndGroup buildingAndGroup = campusCache.getBuildingAndGroup(buildingName);
        Integer buildingId = buildingAndGroup.getBuildingId();


        List<Dormitory> dormitoryList = dormitoryDao.queryAllDormitory();


        return postHandlerDormitoryList(dormitoryList);
    }

    @Override
    public List<DormitoryVO> queryDormitories(Integer buildingId) {

        List<Dormitory> dormitoryList = dormitoryDao.queryDormitoryByBuildingId(buildingId);
        return postHandlerDormitoryList(dormitoryList);

    }

    @Override
    public DormitoryVO queryDormitory(Integer dormitoryId) {


        Dormitory dormitory = dormitoryDao.queryDormitoryByDormitoryId(dormitoryId);

        BuildingAndGroup buildingAndGroup = buildingService.acquireBuildingAndGroupByBuildingID(dormitory.getBuildingId());

        return new DormitoryVO(dormitory, buildingAndGroup);
    }

    @Override
    public DormitoryVO queryDormitory(@NotNull String dormitoryName) {

        Dormitory dormitory = dormitoryDao.queryDormitoryByDormitoryName(dormitoryName);
        BuildingAndGroup buildingAndGroup = buildingService.acquireBuildingAndGroupByBuildingID(dormitory.getBuildingId());

        return new DormitoryVO(dormitory, buildingAndGroup);

    }


    private List<DormitoryVO> postHandlerDormitoryList(List<Dormitory> dormitoryList) {

        Map<Integer, BuildingAndGroup> map = buildingService.acquireBuildingAndGroupMap();
        return dormitoryList.stream().map(dormitory -> new DormitoryVO(dormitory, map.get(dormitory.getBuildingId()))).collect(Collectors.toList());

    }


}
