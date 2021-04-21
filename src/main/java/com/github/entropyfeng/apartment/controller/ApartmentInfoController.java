package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.dao.CampusGroupDao;
import com.github.entropyfeng.apartment.domain.po.CampusGroup;
import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.service.BuildingService;
import com.github.entropyfeng.apartment.service.CampusService;
import com.github.entropyfeng.apartment.service.DormitoryService;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApartmentInfoController {


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

    @GetMapping("/apartment/base/info")
    public Message acquireApartmentBaseInfo(){

        Message message=new Message();

        message.setSuccess(true);
        message.addData("campusNum",campusService.acquireCampusNum());
        message.addData("campusGroupNum",campusService.acquireCampusGroupNum());
        message.addData("buildingNum",buildingService.acquireBuildingNum());
        message.addData("dormitoryNum",dormitoryService.acquireDormitoryNum());
        message.addData("bedNum",dormitoryService.acquireBedNum());
        message.addData("manBedNum",dormitoryService.acquireManBedNum());
        message.addData("woManBedNum",dormitoryService.acquireWomanBedNum());
        return message;
    }
}

