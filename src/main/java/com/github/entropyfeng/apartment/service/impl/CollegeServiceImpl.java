package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.dao.CollegeDao;
import com.github.entropyfeng.apartment.domain.po.College;
import com.github.entropyfeng.apartment.service.CollegeService;
import com.github.entropyfeng.apartment.service.UniversityIdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addNewCollege(String collegeName) {

        collegeDao.insertCollege(idService.getNextCollegeId(),collegeName);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addNewCollege(String collegeName, String description) {
        collegeDao.insertCollegeByDes(idService.getNextCollegeId(),collegeName,description);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCollege(String collegeName) {
        collegeDao.deleteCollegeByCollegeName(collegeName);
    }

    @Override
    public List<College> queryAllColleges() {
        return collegeDao.queryAllCollege();
    }

    @Override
    public List<String> queryAllCollegeNames() {
        return collegeDao.queryAllCollegeNames();
    }
}
