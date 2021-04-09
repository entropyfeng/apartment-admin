package com.github.entropyfeng.apartment.domain.vo;

import com.github.entropyfeng.apartment.domain.po.Dormitory;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.domain.to.StudentResident;
import com.github.entropyfeng.apartment.domain.to.StudentTo;

import java.util.List;

public class DetailDormitory extends DormitoryVO{

    public DetailDormitory(Dormitory dormitory, BuildingAndGroup buildingAndGroup, List<StudentResident> studentList) {
        super(dormitory, buildingAndGroup);
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
