package com.github.entropyfeng.apartment;

import com.github.entropyfeng.apartment.dao.*;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.builder.BuildingVOBuilder;
import com.github.entropyfeng.apartment.domain.builder.DormitoryVOBuilder;
import com.github.entropyfeng.apartment.domain.po.AuthUser;
import com.github.entropyfeng.apartment.domain.po.Building;
import com.github.entropyfeng.apartment.domain.po.CampusGroup;
import com.github.entropyfeng.apartment.domain.vo.BuildingVO;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.service.ApartmentIdService;
import com.github.entropyfeng.apartment.service.BuildingService;
import com.github.entropyfeng.apartment.service.CampusService;
import com.github.entropyfeng.apartment.service.DormitoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitApartmentTest {
    @Autowired
    ApartmentIdService idService;

    @Autowired
    CampusService campusService;

    @Autowired
    BuildingService buildingService;

    @Autowired
    CampusDao campusDao;

    @Autowired
    BuildingDao buildingDao;

    @Autowired
    CampusGroupDao campusGroupDao;

    @Autowired
    DormitoryDao dormitoryDao;
    @Autowired
    DormitoryService dormitoryService;

    @Autowired
    AuthUserDao authUserDao;
    @Autowired
    DormitoryResidentDao residentDao;

    private ThreadLocalRandom random=ThreadLocalRandom.current();

    private void clearAll() {
        idService.clearAllIds();

        dormitoryDao.truncateDormitory();
        buildingDao.truncateBuilding();
        campusDao.truncateCampus();
        campusGroupDao.truncateCampusGroup();
        residentDao.truncateDormitoryResident();
    }

    private void buildCampusAndGroup() {
        campusService.addNewCampus("南京校区", "这是南京校区");
        campusService.addNewCampus("北京校区", "这是北京校区");
        campusService.addNewCampus("天津校区", "这是天津校区");
        campusService.addNewCampus("西安校区", "这是西安校区");

        campusService.addNewCampusGroup("南京桃苑", "南京校区", InGender.MAN,"这是南京桃苑");
        campusService.addNewCampusGroup("南京李苑", "南京校区", InGender.WOMAN,"这是南京李苑");
        campusService.addNewCampusGroup("北京北苑", "北京校区", InGender.MIX,"这是北京北苑");
        campusService.addNewCampusGroup("北京西苑", "北京校区", InGender.MIX,"这是北京西苑");
        campusService.addNewCampusGroup("天津一区", "天津校区", InGender.WOMAN,"这是天津一区");
        campusService.addNewCampusGroup("天津二区", "天津校区", InGender.MAN,"这是天津二区");
        campusService.addNewCampusGroup("天津三区", "天津校区",InGender.MIX, "这是天津三区");
        campusService.addNewCampusGroup("天津四区", "天津校区", InGender.MAN,"这是天津四区");
        campusService.addNewCampusGroup("长安", "西安校区", InGender.MAN,"这是长安");
        campusService.addNewCampusGroup("未央", "西安校区", InGender.MIX,"这是未央");

    }

    private void insertDormitoryBySingleBuilding(Building building) {
        for (int floorNumber = 1; floorNumber <= building.getTotalFloor(); floorNumber++) {
            for (int roomNumber = 1; roomNumber <= 30; roomNumber++) {
                
                DormitoryVOBuilder builder = new DormitoryVOBuilder(building.getBuildingName() + "Floor:" + floorNumber + ":" + roomNumber, building.getBuildingName());
                DormitoryVO simpleDormitoryVO = builder.setFloor(floorNumber).setTotalCapacity(4).setCurrentCapacity(0).setInGender(building.getInGender()).build();

                dormitoryService.addNewDormitory(simpleDormitoryVO);

            }
        }

    }

    private InGender handleRandomGender(InGender inGender){
        if (inGender==InGender.UNKNOWN){
            throw new RuntimeException("can not be unknown");
        }
        if (inGender==InGender.MAN){
            return InGender.MAN;
        }else if (inGender==InGender.WOMAN){
            return InGender.WOMAN;
        }else {
            return random.nextBoolean()?InGender.WOMAN:InGender.MAN;
        }
    }

    private int handleRandomFloor(){
       return random.nextInt(4,8);
    }
    private void buildBuilding() {
        List<CampusGroup> campusGroupList=campusGroupDao.queryAllCampusGroup();

        campusGroupList.forEach(campusGroup -> {
            for (int i = 0; i < 5; i++) {
                BuildingVOBuilder buildingVOBuilder=new BuildingVOBuilder(campusGroup.getCampusGroupName()+i,campusGroup.getCampusGroupName());
                InGender inGender= campusGroup.getInGender();
                BuildingVO buildingVO=buildingVOBuilder.setInGender(handleRandomGender(inGender)).setHasElevator(random.nextBoolean()).setTotalFloor(handleRandomFloor()).build();
                buildingService.addNewBuilding(buildingVO);
            }

        });

    }

    private void buildDormitory() {
        buildingDao.queryAllBuilding().forEach(this::insertDormitoryBySingleBuilding);
    }

    private void buildResidentSkeleton(){

        dormitoryDao.queryAllDormitory().forEach(dormitory ->{
            List<Integer> integers= IntStream.range(0,dormitory.getTotalCapacity()).boxed().collect(Collectors.toList());

            residentDao.insertBatchTemplate(dormitory.getDormitoryId(),integers);

        });

    }
    private void buildResident(){

       List<AuthUser> authUsers= authUserDao.queryAllAuthUser();

    }
    @Test
    public void init() {
        clearAll();
        buildCampusAndGroup();
        buildBuilding();
        buildDormitory();
        buildResidentSkeleton();
    }

}
