package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.vo.BuildingVO;
import com.github.entropyfeng.apartment.service.BuildingService;
import com.github.entropyfeng.common.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuildingController {


    private static final Logger logger= LoggerFactory.getLogger(BuildingController.class);
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/apartment/building/all")
    public Message acquireAllBuilding(){
       List<BuildingVO> buildingVOS= buildingService.acquireAllBuildings();
       Message message=new Message();
       message.setSuccess(true);
       message.addData("buildings",buildingVOS);
       return message;
    }
    @PostMapping("/apartment/building")
    public Message addNewBuilding(@RequestBody BuildingVO buildingVO){
        Message message=new Message();
        boolean success=false;
        try {
            buildingService.addNewBuilding(buildingVO);
            success=true;
        }catch (IllegalArgumentException e){
            message.setMsg("argument error");
            logger.error("buildingVO argument error {}",buildingVO);
        }


        message.setSuccess(success);
        return message;
    }
}
