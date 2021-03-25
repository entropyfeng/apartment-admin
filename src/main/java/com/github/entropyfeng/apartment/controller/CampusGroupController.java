package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.po.CampusGroup;
import com.github.entropyfeng.apartment.service.CampusService;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
