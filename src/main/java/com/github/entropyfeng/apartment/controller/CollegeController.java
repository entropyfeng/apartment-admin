package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.po.College;
import com.github.entropyfeng.apartment.domain.to.CollegeTo;
import com.github.entropyfeng.apartment.service.CollegeService;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/university/college/names")
    public Message acquireCurrentCollegeNames(){
       List<String> names= collegeService.queryAllCollegeNames();

       Message message=new Message();
       message.setSuccess(true);
       message.addData("names",names);
       return message;
    }
    @PostMapping("/university/college")
    public Message addSingleCollege(@RequestBody CollegeTo collegeTo){

        collegeService.addNewCollege(collegeTo.getCollegeName(),collegeTo.getDescription());
        Message message = new Message();
        message.setSuccess(true);
        return message;
    }

    @DeleteMapping("/university/college")
    public Message deleteSingleCollege(@RequestParam("collegeName")String collegeName){
        collegeService.deleteCollege(collegeName);
        Message message = new Message();
        message.setSuccess(true);
        return message;
    }
}
