package com.github.entropyfeng.apartment.service;



import com.github.entropyfeng.apartment.domain.po.College;

import java.util.List;

public interface CollegeService {

    void addNewCollege(String collegeName);
    void addNewCollege(String collegeName,String description);
    void deleteCollege(String collegeName);
    List<College> queryAllColleges();

    List<String> queryAllCollegeNames();
}
