package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.vo.StudentVO;
import com.github.entropyfeng.apartment.service.StudentService;
import com.github.entropyfeng.common.config.anno.CurrentUserAnno;
import com.github.entropyfeng.common.domain.CurrentUser;
import com.github.entropyfeng.common.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
public class StudentController {

    final
    StudentService studentService;

    private static final Logger logger= LoggerFactory.getLogger(StudentController.class);
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/university/student/excel")
    public Message insertStudentsFromExcel(@CurrentUserAnno CurrentUser currentUser, @org.jetbrains.annotations.NotNull @RequestParam("file") MultipartFile file)throws IOException {

        Message message=new Message();
        if (file.getOriginalFilename()==null){
            message.setSuccess(false);
            message.setErrorMessage("file not exist");
            if (logger.isWarnEnabled()){
                logger.warn("file not exists in {} request",currentUser.getUserName());
            }
        }else if (file.getOriginalFilename().endsWith(".xlsx")){
            message.setSuccess(false);
            message.setErrorMessage(String.format("file %s format error", file.getOriginalFilename()));
            if (logger.isWarnEnabled()){
                logger.warn("file {} format not support",file.getOriginalFilename());
            }
        }


        logger.info("original filename {} ,filename {}",file.getOriginalFilename(),file.getName());

        studentService.insertStudentByExcel(file);

        return Message.ok();


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
