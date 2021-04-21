package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.CampusGroup;
import com.github.entropyfeng.apartment.service.CampusService;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampusGroupController {

    @Autowired
    CampusService campusService;

    @GetMapping("/apartment/campusGroup/all")
    public Message acquireAllCampusGroup(){
        List<CampusGroup> campusGroups=campusService.queryAllCampusGroup();
        Message message=new Message();
        message.setSuccess(true);
        message.addData("campusGroups",campusGroups);
        return message;

    }
    @DeleteMapping("/apartment/campusGroup")
    public Message deleteSingleCampusGroup(@RequestParam("campusGroupName")String campusGroupName){

        campusService.deleteCampusGroup(campusGroupName);

        return Message.ok();
    }
    @PostMapping("/apartment/campusGroup")
    public Message addSingleCampusGroup(@RequestParam("campusName")String campusName, @RequestParam("campusGroupName")String campusGroupName, @RequestParam("inGender") InGender inGender, @RequestParam("description")String description){

        campusService.addNewCampusGroup(campusGroupName,campusName,inGender,description);

        return Message.ok();
    }

    @GetMapping("/apartment/campusGroup/names")
    public Message acquireCurrentCampusGroupNames(){
        Message message=new Message();
        message.setSuccess(true);
        List<String> names= campusService.acquireCampusGroupName();
        message.addData("names",names);
        return message;
    }
}
