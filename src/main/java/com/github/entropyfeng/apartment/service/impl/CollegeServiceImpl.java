package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.dao.CollegeDao;
import com.github.entropyfeng.apartment.domain.po.College;
import com.github.entropyfeng.apartment.service.CollegeService;
import com.github.entropyfeng.apartment.service.UniversityIdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {


   private final CollegeDao collegeDao;

   private final UniversityIdService idService;

    @Autowired
    public CollegeServiceImpl(CollegeDao collegeDao, UniversityIdService idService) {
        this.collegeDao = collegeDao;
        this.idService = idService;
    }

    @Override
    public void addNewCollege(String collegeName) {

        collegeDao.insertCollege(idService.getNextCollegeId(),collegeName);
    }

    @Override
    public List<College> queryAllColleges() {
        return collegeDao.queryAllCollege();
    }
}
