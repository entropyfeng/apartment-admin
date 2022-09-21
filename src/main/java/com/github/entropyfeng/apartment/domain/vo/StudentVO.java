package com.github.entropyfeng.apartment.domain.vo;

import com.github.entropyfeng.apartment.domain.AccountStatus;
import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.StudentStatus;
import com.github.entropyfeng.apartment.domain.po.Student;

import java.util.Date;

public class StudentVO {

    private String studentId;
    private String studentName;
    private String idCardNumber;
    private String collegeName;
    private String studentStatus;
    private String email;
    private String phone;
    private Gender gender;
    private boolean inDor;
    private String dormitoryName;
    private Integer bedId;
    private Date createTime;
    private Date updateTime;
    private String registerYear;
    private String description;


    //账号状态
    private String accountStatus;


    public StudentVO() {
    }



    public StudentVO(Student student,String collegeName) {

        this.collegeName=collegeName;
        this.studentId = student.getStudentId();
        this.studentName = student.getStudentName();
        this.idCardNumber = student.getStudentName();
        if (student.getStudentStatus()==null){
            this.studentStatus= StudentStatus.UNKNOWN.name();
        }else {
            this.studentStatus = student.getStudentStatus().name();
        }

        if (student.getAccountStatus()==null){
            this.accountStatus= AccountStatus.UNKNOWN.name();
        }else {
            this.accountStatus=student.getAccountStatus().name();
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


    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
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

    public boolean isInDor() {
        return inDor;
    }

    public void setInDor(boolean inDor) {
        this.inDor = inDor;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public Integer getBedId() {
        return bedId;
    }

    public void setBedId(Integer bedId) {
        this.bedId = bedId;
    }
}
