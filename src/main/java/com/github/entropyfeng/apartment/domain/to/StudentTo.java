package com.github.entropyfeng.apartment.domain.to;

import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.po.Student;

public class StudentTo {

    private String studentId;
    private String studentName;
    private String idCardNumber;
    private String email;
    private String phone;
    private String gender;
    private String collegeName;
    private String registerYear;

    public StudentTo() {

    }

    public StudentTo(Student student, String collegeName) {
        this.studentId = student.getStudentId();
        this.studentName = student.getStudentName();
        this.idCardNumber = student.getIdCardNumber();
        this.email = student.getEmail();
        this.phone = student.getPhone();
        this.gender = student.getGender().name();
        this.collegeName = collegeName;
        this.registerYear = student.getRegisterYear();
    }

    public StudentTo(StudentTo studentTo) {
        this.studentId = studentTo.studentId;
        this.studentName = studentTo.studentName;
        this.idCardNumber = studentTo.idCardNumber;
        this.email = studentTo.email;
        this.phone = studentTo.phone;
        this.gender=studentTo.gender;
        try {
            Integer number = Integer.parseInt(studentTo.gender);
            this.gender= Gender.getInGenderByCode(number).name();
        } catch (Exception e) {
           //ignore
        }
        this.collegeName = studentTo.collegeName;
        this.registerYear = studentTo.registerYear;
    }


    public StudentTo(String studentId, String studentName, String idCardNumber, String email, String phone, String gender, String collegeName, String registerYear) {
        this.studentId = studentId.trim();
        this.studentName = studentName.trim();
        this.idCardNumber = idCardNumber.trim();
        this.email = email.trim();
        this.phone = phone.trim();
        this.gender = gender.trim();
        this.collegeName = collegeName.trim();
        this.registerYear = registerYear.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName.trim();
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender.trim();
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName.trim();
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear.trim();
    }
}
