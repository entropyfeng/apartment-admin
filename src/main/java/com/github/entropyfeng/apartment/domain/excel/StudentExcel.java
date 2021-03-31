package com.github.entropyfeng.apartment.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class StudentExcel   {

    @ExcelProperty("学号")
    private String studentId;

    @ExcelProperty("姓名")
    private String studentName;

    @ExcelProperty("身份证号")
    private String idCardNumber;

    @ExcelProperty("学院名")
    private String collegeName;

    @ExcelProperty("性别")
    private String gender;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("入学年份")
    private String registerYear;


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

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }
}
