package com.github.entropyfeng.apartment.domain.vo;

import com.github.entropyfeng.apartment.domain.po.ApartmentSchedule;

import java.util.List;

public class DetailApartmentSchedule extends ApartmentSchedule {



    public DetailApartmentSchedule(ApartmentSchedule apartmentSchedule){
       List<String> studentIds= apartmentSchedule.getTargetStudentId();

       this.setBeginTime(apartmentSchedule.getBeginTime());
       this.setCreateTime(apartmentSchedule.getCreateTime());
       this.setScheduleName(apartmentSchedule.getScheduleName());
       this.setDescription(apartmentSchedule.getDescription());
       this.setScheduleYear(apartmentSchedule.getScheduleYear());
       this.setCreateTime(apartmentSchedule.getCreateTime());
       this.setUpdateTime(apartmentSchedule.getUpdateTime());
       this.setEndTime(apartmentSchedule.getEndTime());
       this.setTargetBedNum(apartmentSchedule.getTargetBedNum());
       this.setTargetStudentNum(apartmentSchedule.getTargetStudentNum());
       this.setTargetStudentGender(apartmentSchedule.getTargetStudentGender());
    }
}
