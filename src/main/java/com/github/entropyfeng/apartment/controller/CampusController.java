package com.github.entropyfeng.apartment.controller;


import com.github.entropyfeng.apartment.domain.po.Campus;
import com.github.entropyfeng.apartment.service.CampusService;
import com.github.entropyfeng.common.domain.Message;
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
    @PostMapping("/apartment/campus")
    public Message addCampus(@RequestParam("campusName")String campusName){
        Message message=new Message();
        message.setMsg("add campus");
        message.setSuccess(true);
        return message;
    }
    @DeleteMapping("/apartment/campus")
    public Message deleteCampus(@RequestParam("campusName")String campusName){
        Message message=new Message();
        message.setMsg("delete campus");
        message.setSuccess(true);
        return message;
    }
    @GetMapping("/")
    public Message test(){
        Message message=new Message();

        message.setMsg("get");
        message.setSuccess(true);
        return message;
    }
}
