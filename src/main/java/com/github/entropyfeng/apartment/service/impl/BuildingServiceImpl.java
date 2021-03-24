package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.dao.BuildingDao;
import com.github.entropyfeng.apartment.dao.CampusGroupDao;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Building;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.to.CampusAndGroup;
import com.github.entropyfeng.apartment.domain.vo.BuildingVO;
import com.github.entropyfeng.apartment.service.ApartmentIdService;
import com.github.entropyfeng.apartment.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {


    private static final String BUILDING_INFO_FIELD = "bud_info";
    private final CampusGroupDao campusGroupDao;

    private final BuildingDao buildingDao;

    private final ApartmentIdService idService;
    private StringRedisTemplate redisTemplate;

    @Autowired
    public BuildingServiceImpl(StringRedisTemplate redisTemplate, ApartmentIdService idService, CampusGroupDao campusGroupDao, BuildingDao buildingDao) {
        this.campusGroupDao = campusGroupDao;
        this.buildingDao = buildingDao;
        this.idService = idService;
        this.redisTemplate = redisTemplate;
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
    public Map<Integer, BuildingAndGroup> acquireBuildingAndGroupMap() {
        List<BuildingAndGroup> buildingAndGroups = buildingDao.queryRelativeMap();
        Map<Integer, BuildingAndGroup> map = new HashMap<>();

        buildingAndGroups.forEach(buildingAndGroup -> map.put(buildingAndGroup.getBuildingId(), buildingAndGroup));
        redisTemplate.opsForHash().putAll(BUILDING_INFO_FIELD,map);
        return map;
    }

    @Override
    public BuildingAndGroup acquireBuildingAndGroupByBuildingID(@NotNull Integer buildingId) {


        BuildingAndGroup buildingAndGroup = (BuildingAndGroup) redisTemplate.opsForHash().get(BUILDING_INFO_FIELD, buildingId);
        if (buildingAndGroup == null) {
            buildingAndGroup = buildingDao.querySingleRelativeMap(buildingId);

        }
        return buildingAndGroup;
    }
}
