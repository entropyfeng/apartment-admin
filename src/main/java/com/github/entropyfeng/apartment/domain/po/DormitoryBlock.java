package com.github.entropyfeng.apartment.domain.po;

import java.util.List;

public class DormitoryBlock {


    private int dormitoryId;

    private String dormitoryName;

    private List<Integer> bedIds;

    public List<Integer> getBedIds() {
        return bedIds;
    }

    public void setBedIds(List<Integer> bedIds) {
        this.bedIds = bedIds;
    }

    public int getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(int dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

}
