package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.config.cache.CampusCache;
import com.github.entropyfeng.apartment.domain.vo.DetailDormitory;
import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.service.DormitoryService;
import com.github.entropyfeng.common.config.anno.CurrentUserAnno;
import com.github.entropyfeng.common.domain.CurrentUser;
import com.github.entropyfeng.common.domain.Message;
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

    @Autowired
    public DormitoryController(CampusCache campusCache,DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
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

    @GetMapping("/apartment/resident")
    public Message acquireResidentStudent(@RequestParam("")Integer dormitoryId){


        return Message.ok();
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
    public Message acquireDetailDormitory(@ApiIgnore@CurrentUserAnno CurrentUser currentUser,@RequestParam("dormitoryId") String dormitoryId){

       Integer number=Integer.parseInt(dormitoryId);
       DetailDormitory dormitory= dormitoryService.queryDetailDormitory(number,currentUser.getRoles());

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
        if (dormitoryVO==null){
            message.addData("exist",false);
        }else {
            message.addData("dormitory",dormitoryVO);
            message.addData("exist",true);
        }

        return message;
    }
}
