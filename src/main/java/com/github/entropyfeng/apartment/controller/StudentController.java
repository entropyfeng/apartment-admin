package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.vo.StudentVO;
import com.github.entropyfeng.apartment.service.StudentService;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    final
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/university/student/all")
    public Message acquireAllStudent() {

        Message message = new Message();
        List<StudentVO> studentVOList = studentService.queryStudents();
        message.setSuccess(true);
        message.addData("students", studentVOList);
        return message;
    }

}
