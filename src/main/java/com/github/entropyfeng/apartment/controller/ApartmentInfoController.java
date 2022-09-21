package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.service.BuildingService;
import com.github.entropyfeng.apartment.service.CampusService;
import com.github.entropyfeng.apartment.service.DormitoryService;
import com.github.entropyfeng.common.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取当前的首页信息
 */
@RestController
public class ApartmentInfoController {


    private static final Logger logger= LoggerFactory.getLogger(ApartmentInfoController.class);
    final
    BuildingService buildingService;

    final
    CampusService campusService;


    final
    DormitoryService dormitoryService;
    @Autowired
    public ApartmentInfoController(BuildingService buildingService, CampusService campusService, DormitoryService dormitoryService) {
        this.buildingService = buildingService;
        this.campusService = campusService;
        this.dormitoryService = dormitoryService;
    }

    /**
     * 为前端提供图表支持
     * @return {@link Message}
     */
    @GetMapping("/apartment/base/info")
    public Message acquireApartmentBaseInfo(){

        logger.info("call acquireApartmentBaseInfo");
        Message message=new Message();

        message.setSuccess(true);
        message.addData("campusNum",campusService.acquireCampusNum());
        message.addData("campusGroupNum",campusService.acquireCampusGroupNum());
        message.addData("buildingNum",buildingService.acquireBuildingNum());
        message.addData("dormitoryNum",dormitoryService.acquireDormitoryNum());
        message.addData("bedNum",dormitoryService.acquireBedNum());
        message.addData("manBedNum",dormitoryService.acquireManBedNum());
        message.addData("womanBedNum",dormitoryService.acquireWomanBedNum());
        message.addData("currentResident",dormitoryService.acquireResidentNum());
        return message;
    }
}

