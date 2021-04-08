package com.github.entropyfeng.apartment.domain.vo;

import com.github.entropyfeng.apartment.domain.to.StudentResident;
import com.github.entropyfeng.apartment.domain.to.StudentTo;

import java.util.List;

public class DetailDormitory extends DormitoryVO{

    public DetailDormitory(List<StudentResident> studentList) {
        this.studentList = studentList;
    }

    List<StudentResident> studentList;

    public List<StudentResident> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentResident> studentList) {
        this.studentList = studentList;
    }
}
