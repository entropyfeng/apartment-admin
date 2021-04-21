package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.to.StudentTo;
import com.github.entropyfeng.apartment.domain.vo.StudentVO;
import com.github.entropyfeng.apartment.service.StudentService;
import com.github.entropyfeng.apartment.util.FileUtil;
import com.github.entropyfeng.common.config.anno.CurrentUserAnno;
import com.github.entropyfeng.common.domain.CurrentUser;
import com.github.entropyfeng.common.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.Base64;
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

    @GetMapping("/university/student/excel/template")
    public Message downloadInsertStudentTemplate()  {

        byte[] bytes=FileUtil.downloadLocal("excel/BatchInsertStudentTemplate.xlsx");
        Message message=new Message();
        message.setSuccess(true);
        message.addData("file",Base64.getEncoder().encodeToString(bytes));
        message.addData("fileName","BatchInsertStudentTemplate.xlsx");

        return message;
    }

    @PostMapping("/university/student/excel")
    public Message insertStudentsFromExcel(@ApiIgnore @CurrentUserAnno CurrentUser currentUser, @RequestParam("file") MultipartFile file)throws IOException {

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

    @PostMapping("/university/student/account")
    public Message createSingleStudentAccount(@RequestParam("studentId")String studentId){

        studentService.createAccountForSingleStudent(studentId);
        return Message.ok();
    }
    @PutMapping("/university/student/password")
    public Message modifyStudentPassword(@RequestParam("studentId")String studentId,@RequestParam("newPassword")String newPassword){

        studentService.modifyStudentPassword(studentId,newPassword);
        return Message.ok();
    }
    @DeleteMapping("/university/student/account")
    public Message deleteSingleStudentAccount(@RequestParam("studentId")String studentId){

        studentService.deleteAccountForSingleStudent(studentId);
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
    @PutMapping("/university/student")
    public Message modifySingleStudent(@RequestBody StudentTo studentTo){
        studentService.updateStudent(studentTo);
        return Message.ok();
    }
    @PostMapping("/university/student")
    public Message addSingleStudent(@RequestBody StudentTo studentTo){
        studentService.insertStudent(studentTo);
        return Message.ok();
    }

    @DeleteMapping("/university/student")
    public Message deleteSingleStudent(@RequestParam("studentId")String studentId){

        studentService.deleteSingleStudent(studentId);
        return Message.ok();
    }
}
