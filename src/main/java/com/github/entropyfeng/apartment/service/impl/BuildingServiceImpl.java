package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.config.cache.CampusCache;
import com.github.entropyfeng.apartment.dao.BuildingDao;
import com.github.entropyfeng.apartment.dao.CampusGroupDao;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Building;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.to.CampusAndGroup;
import com.github.entropyfeng.apartment.domain.vo.BuildingVO;
import com.github.entropyfeng.apartment.exception.BusinessParaException;
import com.github.entropyfeng.apartment.service.ApartmentIdService;
import com.github.entropyfeng.apartment.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {


    private final CampusGroupDao campusGroupDao;

    private final BuildingDao buildingDao;

    private final ApartmentIdService idService;
    private final CampusCache campusCache;

    @Autowired
    public BuildingServiceImpl(CampusCache campusCache, ApartmentIdService idService, CampusGroupDao campusGroupDao, BuildingDao buildingDao) {
        this.campusGroupDao = campusGroupDao;
        this.buildingDao = buildingDao;
        this.idService = idService;
        this.campusCache=campusCache;
    }


    @Override
    public void addNewBuilding(BuildingVO buildingVO) {

        String campusGroupName = buildingVO.getCampusGroupName();
        if (campusGroupName != null) {
            Integer campusGroupId = campusGroupDao.queryCampusGroupIdByCampusGroupName(campusGroupName);
            if (campusGroupId != null) {
                InGender inGender = InGender.toInGender(buildingVO.getInGender());

                buildingDao.insertBuilding(idService.getNextBuildingId(), campusGroupId, buildingVO.getBuildingName(), buildingVO.getTotalFloor(), buildingVO.getMaxCapacity(), buildingVO.getCurrentCapacity(), buildingVO.getTotalDormitory(), inGender, buildingVO.getDescription(), buildingVO.getHasElevator());
                return;
            }
        }
        throw new IllegalArgumentException("参数错误");
    }

    @Override
    public List<BuildingVO> acquireAllBuildings() {

        Map<Integer, CampusAndGroup> campusGroupIdMap = new HashMap<>();
        List<CampusAndGroup> campusGroupList = campusGroupDao.queryRelativeMap();
        campusGroupList.forEach(temp -> campusGroupIdMap.put(temp.getCampusGroupId(), temp));

        List<BuildingVO> res = new ArrayList<>();
        List<Building> buildingList = buildingDao.queryAllBuilding();
        buildingList.forEach(building -> res.add(new BuildingVO(building, campusGroupIdMap)));
        return res;
    }

    @Override
    public int acquireBuildingNum() {
      return   buildingDao.selectBuildingNum();
    }

    @Override
    public List<String> acquireBuildingNames() {

        return buildingDao.queryAllBuildingNames();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSingleBuilding(String buildingName) {

       Boolean res= buildingDao.queryBuildingRelateStatus(buildingName);

       if (res!=null){
           throw new BusinessParaException("存在与该楼名关联信息");
       }
        buildingDao.deleteBuildingByName(buildingName);

    }

    @Override
    public Map<Integer, BuildingAndGroup> acquireBuildingAndGroupMap() {
        return campusCache.getBuildingIdMap();
    }

    @Override
    public BuildingAndGroup acquireBuildingAndGroupByBuildingID(@NotNull Integer buildingId) {

        return campusCache.getBuildingAndGroup(buildingId);
    }
}
