package com.github.entropyfeng.apartment.service;

import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Campus;
import com.github.entropyfeng.apartment.domain.po.CampusGroup;

import java.util.List;

public interface CampusService {


    int acquireCampusNum();
    int acquireCampusGroupNum();

    void addNewCampus(String campusName,String description);

    void addNewCampusGroup(String campusGroupName, String campusName, InGender inGender, String description);

    void deleteCampus(String campusName);

    void deleteCampusGroup(String campusGroupName);

    List<Campus> queryAllCampus();

    List<CampusGroup> queryAllCampusGroup();

    List<String> acquireCampusNames();

    List<String> acquireCampusGroupName();



}
