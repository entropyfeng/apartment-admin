package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.to.StudentTo;
import com.github.entropyfeng.apartment.domain.vo.StudentVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    void createAccountForAllNonAccountStudent();

    void insertStudentByExcel(MultipartFile file)throws IOException;

    void insertBatchStudent(List<StudentTo> studentList);

    void insertStudent(StudentTo student);

    List<StudentVO> queryStudents();
}
