package com.github.entropyfeng.apartment.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.entropyfeng.apartment.domain.Gender;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApartmentSchedule {

    private String scheduleName;
    private String scheduleYear;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date beginTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    private Gender targetStudentGender;
    private int targetStudentNum;
    private int targetBedNum;
    private boolean scheduleEnable;
    private List<String> targetStudentId;
    private List<CampusGroupBlock> targetApartment;
    private Map<String,String> targetResult;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;


    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleYear() {
        return scheduleYear;
    }

    public void setScheduleYear(String scheduleYear) {
        this.scheduleYear = scheduleYear;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Gender getTargetStudentGender() {
        return targetStudentGender;
    }

    public void setTargetStudentGender(Gender targetStudentGender) {
        this.targetStudentGender = targetStudentGender;
    }

    public int getTargetStudentNum() {
        return targetStudentNum;
    }

    public void setTargetStudentNum(int targetStudentNum) {
        this.targetStudentNum = targetStudentNum;
    }

    public int getTargetBedNum() {
        return targetBedNum;
    }

    public void setTargetBedNum(int targetBedNum) {
        this.targetBedNum = targetBedNum;
    }

    public List<String> getTargetStudentId() {
        return targetStudentId;
    }

    public void setTargetStudentId(List<String> targetStudentId) {
        this.targetStudentId = targetStudentId;
    }

    public List<CampusGroupBlock> getTargetApartment() {
        return targetApartment;
    }

    public void setTargetApartment(List<CampusGroupBlock> targetApartment) {
        this.targetApartment = targetApartment;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Map<String, String> getTargetResult() {
        return targetResult;
    }

    public void setTargetResult(Map<String, String> targetResult) {
        this.targetResult = targetResult;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public boolean isScheduleEnable() {
        return scheduleEnable;
    }

    public void setScheduleEnable(boolean scheduleEnable) {
        this.scheduleEnable = scheduleEnable;
    }
}
