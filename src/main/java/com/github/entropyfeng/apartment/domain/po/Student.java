package com.github.entropyfeng.apartment.domain.po;

import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.StudentAccountStatus;
import com.github.entropyfeng.apartment.domain.StudentStatus;
import com.github.entropyfeng.apartment.domain.vo.StudentVO;
import com.github.entropyfeng.common.core.ChineseIdCardAdvice;
import com.github.entropyfeng.common.core.UsernameAdvice;


import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class Student implements Serializable, ChineseIdCardAdvice, UsernameAdvice {

    private String studentId;
    private String studentName;
    private String idCardNumber;
    private StudentStatus studentStatus;
    private String email;
    private String phone;
    private Gender gender;
    private Integer collegeId;
    //学生账户状态
    private StudentAccountStatus studentAccountStatus;
    private String registerYear;
    private Date createTime;
    private Date updateTime;
    private String description;

    public Student(){

    }

    public Student(String studentId, String studentName, String idCardNumber, Integer apartmentId, StudentStatus studentStatus, String email, String phone, Gender gender, Integer collegeId,  StudentAccountStatus studentAccountStatus, String registerYear, String description) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.idCardNumber = idCardNumber;
        this.studentStatus = studentStatus;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.collegeId = collegeId;
        this.studentAccountStatus = studentAccountStatus;
        this.registerYear = registerYear;
        this.description = description;
    }

    public Student(StudentVO studentVO, ConcurrentHashMap<String,Integer> collegeNameIdMap){
        this.studentId = studentVO.getStudentId();
        this.studentName = studentVO.getStudentName();
        this.idCardNumber = studentVO.getIdCardNumber();
        this.studentStatus = StudentStatus.valueOf(studentVO.getStudentStatus());
        this.email =studentVO.getEmail();
        this.phone = studentVO.getPhone();
        this.gender = studentVO.getGender();
        this.collegeId = collegeNameIdMap.get(studentVO.getCollegeName());
        this.studentAccountStatus = StudentAccountStatus.valueOf(studentVO.getStudentAccountStatus());
        this.registerYear = studentVO.getRegisterYear();
        this.description = studentVO.getDescription();
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public StudentAccountStatus getStudentAccountStatus() {
        return studentAccountStatus;
    }

    public void setStudentAccountStatus(StudentAccountStatus studentAccountStatus) {
        this.studentAccountStatus = studentAccountStatus;
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

    @Override
    public String getChinaIdCardNumber() {
        return idCardNumber;
    }

    @Override
    public String getUsername() {
        return studentId;
    }
}
