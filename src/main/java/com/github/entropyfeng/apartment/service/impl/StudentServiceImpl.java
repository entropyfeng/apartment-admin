package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.config.cache.CollegeCache;
import com.github.entropyfeng.apartment.dao.StudentDao;
import com.github.entropyfeng.apartment.domain.StudentAccountStatus;
import com.github.entropyfeng.apartment.domain.po.Student;
import com.github.entropyfeng.apartment.domain.vo.StudentVO;
import com.github.entropyfeng.apartment.service.AuthUserService;
import com.github.entropyfeng.apartment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

       List<Student> students= studentDao.queryAllStudents();

       List<Student> noAccountStudents= students.stream().filter(student -> student.getStudentAccountStatus().equals(StudentAccountStatus.NOT_EXIST)).collect(Collectors.toList());

       noAccountStudents.forEach(student ->{
           authUserService.registerUser(student.getUsername(),student.getUsername(),student.getEmail(),student.getPhone(),false);
           studentDao.updateAccountStatus(student.getStudentId(),StudentAccountStatus.EXIST);
       } );


    }

    @Override
    public List<StudentVO> queryStudents() {
      return   studentDao.queryAllStudents().stream().map(student -> new StudentVO(student,collegeCache.getName(student.getCollegeId()))).collect(Collectors.toList());

    }
}
