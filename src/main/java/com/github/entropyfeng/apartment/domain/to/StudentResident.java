package com.github.entropyfeng.apartment.domain.to;

public class StudentResident extends StudentTo {


    public StudentResident(StudentTo studentTo,Integer bedId){
        super(studentTo);
        this.bedId=bedId;
    }
    private Integer bedId;

    public Integer getBedId() {
        return bedId;
    }

    public void setBedId(Integer bedId) {
        this.bedId = bedId;
    }
}
