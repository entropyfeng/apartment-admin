package com.github.entropyfeng.apartment.domain.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.github.entropyfeng.apartment.domain.to.StudentTo;

import java.util.List;

public class StudentExcelListener extends AnalysisEventListener<StudentExcel> {

    public StudentExcelListener(List<StudentTo> studentList) {
        this.studentList = studentList;
    }

    private List<StudentTo> studentList;
    @Override
    public void invoke(StudentExcel data, AnalysisContext context) {

        StudentTo studentTo=new StudentTo(data.getStudentId(),data.getStudentName(),data.getIdCardNumber(),data.getEmail(),data.getPhone(),data.getGender(),data.getCollegeName(),data.getRegisterYear());
        studentList.add(studentTo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
