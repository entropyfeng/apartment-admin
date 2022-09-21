package com.github.entropyfeng.apartment.domain.to;

import com.github.entropyfeng.apartment.domain.Gender;

public class StudentQuery {

    String studentId;
    String studentName;
    String collegeName;
    String registerYear;
    Gender gender;
    boolean andOp;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isAndOp() {
        return andOp;
    }

    public void setAndOp(boolean andOp) {
        this.andOp = andOp;
    }
}
