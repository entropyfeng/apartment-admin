package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.config.cache.CampusCache;
import com.github.entropyfeng.apartment.domain.DormitoryDirection;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.vo.DetailDormitory;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.service.DormitoryService;
import com.github.entropyfeng.common.config.anno.CurrentUserAnno;
import com.github.entropyfeng.common.domain.CurrentUser;
import com.github.entropyfeng.common.domain.Message;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
public class DormitoryController {

    private final DormitoryService dormitoryService;

    private final CampusCache campusCache;
    @Autowired
    public DormitoryController(CampusCache campusCache,DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
        this.campusCache=campusCache;
    }

/*
    @GetMapping("/apartment/dormitory")
    public Message acquireDormitoryByBuildingId(@Param("buildingId")Integer buildingId){

        Message message=new Message();
        List<DormitoryVO> dormitoryVOList= dormitoryService.queryDormitories(buildingId);
        message.setSuccess(true);
        message.addData("dormitories",dormitoryVOList);
        return message;
    }*/

    @GetMapping("/apartment/dormitory/all")
    public Message acquireAllDormitory(){
        Message message=new Message();
        message.setSuccess(true);
       List<DormitoryVO> dormitoryVOS= dormitoryService.queryAllDormitories();

       message.addData("dormitories",dormitoryVOS);
       return message;
    }

    @GetMapping("/apartment/dormitory")
    public Message acquireDormitory(@RequestBody DormitoryVO dormitoryVO){


       List<DormitoryVO> dormitoryList= dormitoryService.queryDormitory(dormitoryVO);

       Message message=new Message();
       message.setSuccess(true);
       message.addData("dormitories",dormitoryList);
       return message;
    }

    @GetMapping("/apartment/detail/dormitory")
    public Message acquireDetailDormitory(@RequestParam("dormitoryId") Integer dormitoryId){


       DetailDormitory dormitory= dormitoryService.queryDetailDormitory(dormitoryId);

       Message message=new Message();
       message.setSuccess(true);
       message.addData("dormitory",dormitory);
       return message;


    }

    @GetMapping("/apartment/dormitory/my")
    public Message acquireMyDormitory(@ApiIgnore @CurrentUserAnno CurrentUser currentUser){
        Message message=new Message();
        String username=currentUser.getUserName();

        DetailDormitory dormitoryVO= dormitoryService.queryMyDormitory(username);
        message.setSuccess(true);
        message.addData("dormitory",dormitoryVO);
        return message;
    }
}
