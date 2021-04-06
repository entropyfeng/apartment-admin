package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.config.cache.CampusCache;
import com.github.entropyfeng.apartment.dao.BuildingDao;
import com.github.entropyfeng.apartment.dao.DormitoryDao;
import com.github.entropyfeng.apartment.dao.DormitoryResidentDao;
import com.github.entropyfeng.apartment.dao.ResidentDao;
import com.github.entropyfeng.apartment.domain.DormitoryDirection;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.to.*;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.domain.vo.SimpleDormitoryVO;
import com.github.entropyfeng.apartment.service.ApartmentIdService;
import com.github.entropyfeng.apartment.service.BuildingService;
import com.github.entropyfeng.apartment.service.DormitoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DormitoryServiceImpl implements DormitoryService {


    private static final Logger logger = LoggerFactory.getLogger(DormitoryServiceImpl.class);


    @Autowired
    public DormitoryServiceImpl(BuildingService buildingService, CampusCache campusCache, ResidentDao residentDao, ApartmentIdService idService, DormitoryResidentDao dormitoryResidentDao, DormitoryDao dormitoryDao, BuildingDao buildingDao) {
        this.dormitoryDao = dormitoryDao;
        this.residentDao = residentDao;
        this.buildingDao = buildingDao;
        this.idService = idService;
        this.dormitoryResidentDao = dormitoryResidentDao;
        this.buildingService = buildingService;
        this.campusCache = campusCache;
    }

    private final ApartmentIdService idService;

    private final CampusCache campusCache;
    private final DormitoryDao dormitoryDao;

    private final BuildingDao buildingDao;

    private final ResidentDao residentDao;

    private final DormitoryResidentDao dormitoryResidentDao;

    private final BuildingService buildingService;

    @Override
    public void addNewDormitory(SimpleDormitoryVO simpleDormitoryVO) {
        if (StringUtils.isEmpty(simpleDormitoryVO.getDormitoryName())) {
            String err = "lack of dormitoryName";
            logger.error(err);
            throw new IllegalArgumentException(err);
        }

        if (!StringUtils.isEmpty(simpleDormitoryVO.getBuildingName())) {
            addNewDormitoryByBuildingName(simpleDormitoryVO);
        } else {
            String error = "lack of relative buildingInfo";
            logger.error(error);
            throw new IllegalArgumentException(error);
        }

    }


    private void addNewDormitoryByBuildingName(SimpleDormitoryVO simpleDormitoryVO) {

        String buildingName = simpleDormitoryVO.getBuildingName();
        Integer buildingId = buildingDao.queryBuildingIdByBuildingName(buildingName);
        if (buildingId != null) {
            dormitoryDao.insertDormitory(idService.getNextDormitoryId(), buildingId, simpleDormitoryVO.getFloor(), simpleDormitoryVO.getDormitoryName(), simpleDormitoryVO.getTotalCapacity(), InGender.toInGender(simpleDormitoryVO.getInGender()), DormitoryDirection.toDormitoryDirection(simpleDormitoryVO.getDormitoryDirection()), simpleDormitoryVO.getDescription());
            return;
        }
        String errorInfo = "not exists building";
        logger.error(errorInfo);
        throw new IllegalArgumentException(errorInfo);

    }


    @Override
    public List<SimpleDormitoryVO> queryAllDormitories() {
        List<Dormitory> dormitoryList = dormitoryDao.queryAllDormitory();
        return postHandlerDormitoryList(dormitoryList);
    }

    @Override
    public List<SimpleDormitoryVO> queryDormitories(Integer buildingId) {
        List<Dormitory> dormitoryList = dormitoryDao.queryDormitoryByBuildingId(buildingId);
        return postHandlerDormitoryList(dormitoryList);

    }

    @Override
    public SimpleDormitoryVO querySimpleDormitory(Integer dormitoryId) {

        Dormitory dormitory = dormitoryDao.queryDormitoryByDormitoryId(dormitoryId);

        BuildingAndGroup buildingAndGroup = buildingService.acquireBuildingAndGroupByBuildingID(dormitory.getBuildingId());

        return new SimpleDormitoryVO(dormitory, buildingAndGroup);
    }

    @Override
    public SimpleDormitoryVO querySimpleDormitory(@NotNull String dormitoryName) {
        Dormitory dormitory = dormitoryDao.queryDormitoryByDormitoryName(dormitoryName);
        BuildingAndGroup buildingAndGroup = buildingService.acquireBuildingAndGroupByBuildingID(dormitory.getBuildingId());

        return new SimpleDormitoryVO(dormitory, buildingAndGroup);

    }

    @Override
    public DormitoryVO acquireDormitory(@NotNull String dormitoryName) {
        Dormitory dormitory = dormitoryDao.queryDormitoryByDormitoryName(dormitoryName);
        Integer buildingId = dormitory.getBuildingId();
        return new DormitoryVO(dormitory, buildingService.acquireBuildingAndGroupByBuildingID(buildingId));
    }

    @Override
    public DormitoryVO acquireUserDormitory(@NotNull String residentId) {

        Dormitory dormitory = dormitoryDao.queryDormitoryByResidentId(residentId);
        Integer buildingId = dormitory.getBuildingId();

        return new DormitoryVO(dormitory, buildingService.acquireBuildingAndGroupByBuildingID(buildingId));

    }


    private List<SimpleDormitoryVO> postHandlerDormitoryList(List<Dormitory> dormitoryList) {

        Map<Integer, BuildingAndGroup> map = buildingService.acquireBuildingAndGroupMap();
        return dormitoryList.stream().map(dormitory -> new SimpleDormitoryVO(dormitory, map.get(dormitory.getBuildingId()))).collect(Collectors.toList());

    }


}
