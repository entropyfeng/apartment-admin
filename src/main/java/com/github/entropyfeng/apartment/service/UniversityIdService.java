package com.github.entropyfeng.apartment.service;

public interface UniversityIdService {

    String COLLEGE_FIELD_NAME ="college";
    Integer getNextCollegeId();

    void clearAll();
}
