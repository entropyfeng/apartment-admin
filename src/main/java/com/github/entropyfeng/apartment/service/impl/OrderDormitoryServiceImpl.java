package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.dao.*;
import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.to.DormitoryIdAndVersion;
import com.github.entropyfeng.apartment.domain.to.ResidentBedVersion;
import com.github.entropyfeng.apartment.domain.vo.SimpleDormitoryVO;
import com.github.entropyfeng.apartment.exception.BusinessParaException;
import com.github.entropyfeng.apartment.service.BuildingService;
import com.github.entropyfeng.apartment.service.OrderDormitoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderDormitoryServiceImpl implements OrderDormitoryService {

    Logger logger = LoggerFactory.getLogger(OrderDormitoryServiceImpl.class);
    final
    CampusGroupDao campusGroupDao;
    final
    CampusDao campusDao;
    final
    BuildingDao buildingDao;

    final
    DormitoryDao dormitoryDao;
    final DormitoryResidentDao dormitoryResidentDao;
    final ResidentDao residentDao;
    final BuildingService buildingService;


    @Autowired
    public OrderDormitoryServiceImpl(BuildingService buildingService, ResidentDao residentDao, CampusGroupDao campusGroupDao, CampusDao campusDao, BuildingDao buildingDao, DormitoryDao dormitoryDao, DormitoryResidentDao dormitoryResidentDao) {
        this.campusGroupDao = campusGroupDao;
        this.campusDao = campusDao;
        this.buildingDao = buildingDao;
        this.dormitoryDao = dormitoryDao;
        this.dormitoryResidentDao = dormitoryResidentDao;
        this.buildingService = buildingService;
        this.residentDao = residentDao;
    }

    @Override
    public List<String> filterAvailableCampusName(@NotNull String residentId) {
        return campusDao.queryAllCampusName();
    }

    @Override
    public List<String> filterAvailableCampusGroup(@NotNull String residentId, @NotNull String campusName) {


        InGender inGender = acquireInGender(residentId);
        return campusGroupDao.queryAvailableCampusAndGroupName(campusName, inGender, InGender.MIX);
    }

    public List<String> filterAvailableBuildingName(@NotNull String residentId, @NotNull String campusGroupName) {
        InGender gender = acquireInGender(residentId);

        return buildingDao.queryAvailableBuildingName(campusGroupName, gender);

    }

    @Override
    public List<SimpleDormitoryVO> filterAvailableDormitory(@NotNull String residentId, @NotNull String buildingName) {

        InGender gender = acquireInGender(residentId);
        List<Dormitory> dormitoryList = dormitoryDao.queryFilterDormitoryByBuildingName(buildingName, gender);
        return postHandlerDormitoryList(dormitoryList);
    }


    @Override
    public Boolean hasInDormitory(@NotNull String residentId) {
        return dormitoryResidentDao.querySingleResidentStatus(residentId);
    }

    /**
     * 预定寝室
     *
     * @param residentId  用户名
     * @param dormitoryId 寝室Id
     * @param bedId       床位号
     * @return true->预定成功; false->预定失败
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean checkInDormitory(@NotNull String residentId, @NotNull Integer dormitoryId, @NotNull Integer bedId) {

        Boolean inDormitory = dormitoryResidentDao.querySingleResidentStatus(residentId);
        if (inDormitory) {
            return Boolean.FALSE;
        }
        Dormitory dormitory = dormitoryDao.queryDormitoryByDormitoryId(dormitoryId);
        if (dormitory != null) {
            Integer currentCapacity = dormitory.getCurrentCapacity();
            if (currentCapacity < dormitory.getTotalCapacity()) {
                ResidentBedVersion residentBedVersion = dormitoryResidentDao.queryCurrentBedInfo(dormitoryId, bedId);
                String currentResidentName = residentBedVersion.getResidentName();
                if (currentResidentName == null) {
                    Long residentVersion = residentBedVersion.getVersion();
                    Integer count = dormitoryResidentDao.updateDormitoryResidentRelyVersion(dormitoryId, bedId, residentId, residentVersion);
                    if (count.equals(1)) {

                        Long dormitoryVersion = dormitory.getVersion();
                        Integer dormitoryCount = dormitoryDao.updateCurrentCapacityRelyVersion(dormitoryId, currentCapacity + 1, dormitoryVersion);

                        if (dormitoryCount.equals(1)) {
                            return Boolean.TRUE;
                        }

                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    }
                }
            }
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean checkInDormitory(@NotNull String residentId, @NotNull Integer dormitoryId) {

        List<Integer> bedIdList = dormitoryResidentDao.queryAvailableBed(dormitoryId);

        boolean res;
        for (Integer bedId : bedIdList) {

            res = checkInDormitory(residentId, dormitoryId, bedId);
            if (res) {
                return true;
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean quitDormitory(@NotNull String residentId) {


        DormitoryIdAndVersion dormitoryIdAndVersion = dormitoryResidentDao.queryIdAndVersionByResidentName(residentId);

        Integer dormitoryId = dormitoryIdAndVersion.getDormitoryId();
        Dormitory dormitory = dormitoryDao.queryDormitoryByDormitoryId(dormitoryId);
        Integer currentCapacity = dormitory.getCurrentCapacity();
        if (currentCapacity <= 0) {
            return false;
        }
        Integer residentCount = dormitoryResidentDao.updateQuitDormitory(dormitoryId, residentId, dormitoryIdAndVersion.getVersion());
        if (residentCount == 1) {
            Integer dormitoryCount = dormitoryDao.updateCurrentCapacityRelyVersion(dormitoryId, currentCapacity - 1, dormitory.getVersion());

            if (dormitoryCount == 1) {
                return true;
            }

        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return false;
    }


    private List<SimpleDormitoryVO> postHandlerDormitoryList(List<Dormitory> dormitoryList) {

        Map<Integer, BuildingAndGroup> map = buildingService.acquireBuildingAndGroupMap();
        return dormitoryList.stream().map(dormitory -> new SimpleDormitoryVO(dormitory, map.get(dormitory.getBuildingId()))).collect(Collectors.toList());

    }

    private InGender acquireInGender(String residentId) {
        Gender gender = residentDao.selectGenderFromResidentId(residentId);
        InGender inGender;
        switch (gender) {
            case MAN:
                inGender = InGender.MAN;
                break;
            case WOMAN:
                inGender = InGender.WOMAN;
                break;
            default: {
                if (logger.isWarnEnabled()) {
                    logger.warn("residentId {} 's gender is unknown ", residentId);
                }
                throw new BusinessParaException(String.format("residentId %s 's gender is unknown ", residentId));

            }
        }
        return inGender;
    }
}
