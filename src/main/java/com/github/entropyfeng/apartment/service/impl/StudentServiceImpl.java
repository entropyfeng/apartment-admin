package com.github.entropyfeng.apartment.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.entropyfeng.apartment.config.cache.CollegeCache;
import com.github.entropyfeng.apartment.dao.StudentDao;
import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.StudentAccountStatus;
import com.github.entropyfeng.apartment.domain.excel.StudentExcel;
import com.github.entropyfeng.apartment.domain.excel.StudentExcelListener;
import com.github.entropyfeng.apartment.domain.po.Student;
import com.github.entropyfeng.apartment.domain.to.StudentTo;
import com.github.entropyfeng.apartment.domain.vo.StudentVO;
import com.github.entropyfeng.apartment.service.AuthUserService;
import com.github.entropyfeng.apartment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    CollegeCache collegeCache;
    @Autowired
    AuthUserService authUserService;

    @Override
    public void createAccountForAllNonAccountStudent() {

        List<Student> students = studentDao.queryAllStudents();


        List<Student> noAccountStudents = students.stream().filter(student -> student.getStudentAccountStatus().equals(StudentAccountStatus.NOT_EXIST)).collect(Collectors.toList());

        noAccountStudents.forEach(student -> {
            authUserService.registerUser(student.getUsername(), student.getUsername(), student.getEmail(), student.getPhone(), false);
            studentDao.updateAccountStatus(student.getStudentId(), StudentAccountStatus.EXIST);
        });


    }

    @Override
    public void insertStudentByExcel(MultipartFile file) throws IOException {

        List<StudentTo> studentTos = new ArrayList<>();
        StudentExcelListener listener = new StudentExcelListener(studentTos);
        EasyExcel.read(file.getInputStream(), StudentExcel.class, listener);
        insertBatchStudent(studentTos);
    }

    @Override
    public void insertBatchStudent(List<StudentTo> studentList) {
        studentList.forEach(this::insertStudent);
    }

    @Override
    public void insertStudent(StudentTo studentTo) {
        Student student = new Student();
        student.setStudentId(studentTo.getStudentId());
        student.setStudentName(studentTo.getStudentName());
        student.setIdCardNumber(studentTo.getIdCardNumber());
        student.setCollegeId(collegeCache.getId(studentTo.getCollegeName()));
        student.setPhone(studentTo.getPhone());
        student.setEmail(studentTo.getEmail());
        student.setRegisterYear(studentTo.getRegisterYear());
        student.setGender(Gender.toInGender(studentTo.getGender()));

        studentDao.insertStudent(student);
    }

    @Override
    public List<StudentVO> queryStudents() {
        return studentDao.queryAllStudents().stream().map(student -> new StudentVO(student, collegeCache.getName(student.getCollegeId()))).collect(Collectors.toList());

    }
}
