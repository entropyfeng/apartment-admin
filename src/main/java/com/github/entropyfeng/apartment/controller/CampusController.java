package com.github.entropyfeng.apartment.controller;


import com.github.entropyfeng.apartment.domain.po.Campus;
import com.github.entropyfeng.apartment.service.CampusService;
import com.github.entropyfeng.common.domain.Message;
import org.apache.xmlbeans.impl.jam.mutable.MElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampusController {

    @Autowired
    CampusService campusService;

    @GetMapping("/apartment/campus/all")
    public Message acquireAllCampus(){
        Message message=new Message();
        message.setMsg("get campus");
        List<Campus> campuses= campusService.queryAllCampus();
        message.setSuccess(true);
        message.addData("campuses",campuses);
        return message;
    }

    @GetMapping("/apartment/campus/names")
    public Message acquireCurrentCampusNames(){
        Message message=new Message();
        message.setSuccess(true);
       List<String> names= campusService.acquireCampusNames();
        message.addData("names",names);
        return message;
    }
    @PostMapping("/apartment/campus")
    public Message addSingleCampus(@RequestParam("campusName")String campusName,@RequestParam("description")String description){
        Message message=new Message();
        campusService.addNewCampus(campusName,description);
        message.setMsg("add campus");
        message.setSuccess(true);
        return message;
    }
    @DeleteMapping("/apartment/campus")
    public Message deleteSingleCampus(@RequestParam("campusName")String campusName){
        Message message=new Message();
        campusService.deleteCampus(campusName);
        message.setMsg("delete campus");
        message.setSuccess(true);
        return message;
    }

}
