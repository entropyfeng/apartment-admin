package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.to.StudentTo;
import com.github.entropyfeng.apartment.domain.vo.StudentVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    void createAccountForAllNonAccountStudent(int limit,List<String> roleNames);

    void createAccountForNonAccountStudent(List<String> studentIds,List<String> roleNames);

    void createAccountForSingleStudent(String studentId);

    void modifyStudentPassword(String studentId,String newPassword);
    void deleteAccountForSingleStudent(String studentId);

    void insertStudentByExcel(MultipartFile file)throws IOException;

    void insertBatchStudent(List<StudentTo> studentList);

    void insertStudent(StudentTo student);

    void deleteSingleStudent(String studentId);
    void updateStudent(StudentTo studentTo);
    List<StudentVO> queryStudents();
}
