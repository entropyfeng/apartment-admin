package com.github.entropyfeng.apartment.domain.po;

import java.util.Date;
import java.util.List;

public class ResidentBlock {

    private List<Integer> bedIds;
    private List<String> studentIds;
    private List<Date> checkInTimes;

    public List<Integer> getBedIds() {
        return bedIds;
    }

    public void setBedIds(List<Integer> bedIds) {
        this.bedIds = bedIds;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }

    public List<Date> getCheckInTimes() {
        return checkInTimes;
    }

    public void setCheckInTimes(List<Date> checkInTimes) {
        this.checkInTimes = checkInTimes;
    }
}
