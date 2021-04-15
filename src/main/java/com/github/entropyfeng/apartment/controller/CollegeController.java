package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.po.College;
import com.github.entropyfeng.apartment.service.CollegeService;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CollegeController {

    final
    CollegeService collegeService;

    @Autowired
    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("/university/college/all")
    public Message acquireAllCollege() {
        Message message = new Message();
        List<College> colleges = collegeService.queryAllColleges();
        message.setSuccess(true);
        message.addData("colleges", colleges);
        return message;
    }
}
