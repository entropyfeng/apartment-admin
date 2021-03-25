package com.github.entropyfeng.apartment.service;

public interface UniversityIdService {

    String DEPARTMENT_FIELD_NAME="dep";
    Integer getNextCollegeId();

    void clearAll();
}
