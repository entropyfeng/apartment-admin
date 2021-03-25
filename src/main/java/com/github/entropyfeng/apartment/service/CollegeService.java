package com.github.entropyfeng.apartment.service;



import com.github.entropyfeng.apartment.domain.po.College;

import java.util.List;

public interface CollegeService {

    void addNewCollege(String collegeName);

    List<College> queryAllColleges();

}
