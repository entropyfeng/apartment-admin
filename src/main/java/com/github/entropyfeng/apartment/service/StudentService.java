package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.vo.StudentVO;

import java.util.List;

public interface StudentService {

    void createAccountForAllNonAccountStudent();

    List<StudentVO> queryStudents();
}
