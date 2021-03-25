package com.github.entropyfeng.apartment.domain.vo;



import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.StudentAccountStatus;
import com.github.entropyfeng.apartment.domain.StudentStatus;
import com.github.entropyfeng.apartment.domain.po.Student;

import java.util.Date;

public class StudentVO {

    private String studentId;
    private String studentName;
    private String idCardNumber;
    private String collegeName;
    private StudentStatus studentStatus;
    private String email;
    private String phone;
    private Gender gender;
    private Date createTime;
    private Date updateTime;
    private String registerYear;
    private String description;


    //账号状态
    private StudentAccountStatus studentAccountStatus;
    public StudentVO() {
    }



    public StudentVO(Student student,String collegeName) {

        this.collegeName=collegeName;
        this.studentId = student.getStudentId();
        this.studentName = student.getStudentName();
        this.idCardNumber = student.getStudentName();
        if (student.getStudentStatus()==null){
            this.studentStatus= StudentStatus.UNKNOWN;
        }else {
            this.studentStatus = student.getStudentStatus();
        }

        if (student.getStudentAccountStatus()==null){
            this.studentAccountStatus=StudentAccountStatus.UNKNOWN;
        }else {
            this.studentAccountStatus=student.getStudentAccountStatus();
        }

        this.email = student.getEmail();
        this.phone = student.getPhone();
        if (student.getGender()==null){
            this.gender=Gender.UNKNOWN;
        }else {
            this.gender=student.getGender();
        }
        this.createTime = student.getCreateTime();
        this.updateTime = student.getUpdateTime();
        this.registerYear=student.getRegisterYear();
        this.description = student.getDescription();
    }

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


    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }




    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public StudentAccountStatus getStudentAccountStatus() {
        return studentAccountStatus;
    }

    public void setStudentAccountStatus(StudentAccountStatus studentAccountStatus) {
        this.studentAccountStatus = studentAccountStatus;
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
