package com.github.entropyfeng.apartment.dao;


import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.StudentAccountStatus;
import com.github.entropyfeng.apartment.domain.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentDao {


    void insertStudent(@Param("student") Student student);

    void insertBatchStudent(@Param("list")List<Student> student);

    void insertStudentContainsCollege(@Param("studentId") String studentId, @Param("studentName") String studentName, @Param("collegeId") Integer collegeId,@Param("gender")Gender gender);

    void truncateStudent();

    void deleteStudentByStudentName(@Param("studentName") String studentName);

    void deleteStudentByStudentId(@Param("studentId") String studentId);

    void updateStudentCollege(@Param("studentId") String studentId, @Param("collegeId") Integer collegeId);

    Student queryStudentByStudentName(@Param("studentName") String studentName);

    Student queryStudentByStudentId(@Param("studentId") String studentId);


    List<Student> queryAllStudents();

    List<Student> queryStudentsByStudentIds(@Param("list") List<String> list);

    List<Student> queryStudentsByCollegeId(@Param("collegeId") Integer collegeId);


    Gender queryStudentGenderByStudentId(@Param("studentId")String studentId);

    String queryStudentNameByStudentId(@Param("studentId")String studentId);


    void updateAccountStatus(@Param("studentId")String studentId,@Param("status") StudentAccountStatus status);

    //void updateBatchAccountStatus(@Param("list")List<String> studentIdList,@Param("status")StudentAccountStatus status);

    String queryCollegeNameByStudentId(@Param("studentId") String studentId);

}

