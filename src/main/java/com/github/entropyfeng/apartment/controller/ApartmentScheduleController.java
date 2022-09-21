package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.po.ApartmentSchedule;
import com.github.entropyfeng.apartment.domain.to.ScheduleStudentIdTo;
import com.github.entropyfeng.apartment.domain.vo.DetailApartmentSchedule;
import com.github.entropyfeng.apartment.domain.vo.SimpleStudentInfo;
import com.github.entropyfeng.apartment.service.ApartmentScheduleService;
import com.github.entropyfeng.apartment.service.StudentService;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ApartmentScheduleController {

    @Autowired
    ApartmentScheduleService scheduleService;

    @Autowired
    StudentService studentService;

    @PostMapping("/apartment/schedule/enable")
    public Message enableOrDisableSchedule(@RequestParam("scheduleName") String scheduleName, @RequestParam("enable") boolean enable) {

        Message message = new Message();
        scheduleService.enableOrDisableSchedule(scheduleName, enable);
        message.setSuccess(true);
        return message;
    }

    @GetMapping("/apartment/schedule/all")
    public Message acquireAllApartmentSchedule() {
        Message message = new Message();

        List<ApartmentSchedule> schedules = scheduleService.acquireAllApartmentSchedule();
        message.addData("schedules", schedules);
        message.setSuccess(true);
        return message;
    }

    @PostMapping("/apartment/schedule")
    public Message addApartmentSchedule(@RequestBody ApartmentSchedule apartmentSchedule) {

        String scheduleName = apartmentSchedule.getScheduleName();
        String scheduleYear = apartmentSchedule.getScheduleYear();
        Date beginTime = apartmentSchedule.getBeginTime();
        Date endTime = apartmentSchedule.getEndTime();
        Gender gender = apartmentSchedule.getTargetStudentGender();
        Message message = new Message();
        message.setSuccess(true);

        scheduleService.createApartmentSchedule(scheduleName, scheduleYear, beginTime, endTime, gender);
        return message;
    }

    @GetMapping("/apartment/schedule/detail")
    public Message acquireDetailApartmentSchedule(@RequestParam("scheduleName") String scheduleName) {

        System.out.println(scheduleName);
        DetailApartmentSchedule schedule = scheduleService.acquireDetailApartmentSchedule(scheduleName);
        Message message = new Message();
        message.setSuccess(true);
        message.addData("schedule", schedule);

        return message;
    }

    @GetMapping("/apartment/schedule/student/detail")
    public Message acquireStudentInfoByScheduleName(@RequestParam("scheduleName") String scheduleName) {

        List<String> ids = scheduleService.acquireStudentIdList(scheduleName);
        Message message = new Message();
        List<SimpleStudentInfo> students = null;
        message.setSuccess(true);
        if (!ids.isEmpty()) {
            students = studentService.querySimpleStudentInfoList(ids);
        }
        if (students == null) {
            students = new ArrayList<>();
        }


        message.addData("students", students);

        return message;
    }

    @GetMapping("/apartment/schedule/student/available")
    public Message acquireAvailableStudentInfoByScheduleName(@RequestParam("scheduleName") String scheduleName) {


        return Message.ok();
    }

    @PostMapping("/apartment/schedule/student")
    public Message insertStudentIntoSchedule(@RequestBody ScheduleStudentIdTo data) {

        return Message.ok();
    }

    @DeleteMapping("/apartment/schedule/student")
    public Message removeStudentFromSchedule(@RequestBody ScheduleStudentIdTo data) {


        return Message.ok();
    }


}
