package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.dao.CampusDao;
import com.github.entropyfeng.apartment.dao.CampusGroupDao;
import com.github.entropyfeng.apartment.domain.InGender;
import com.github.entropyfeng.apartment.domain.po.Campus;
import com.github.entropyfeng.apartment.domain.po.CampusGroup;
import com.github.entropyfeng.apartment.service.ApartmentIdService;
import com.github.entropyfeng.apartment.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CampusServiceImpl implements CampusService {


    private final CampusDao campusDao;


    private final CampusGroupDao campusGroupDao;


    private final ApartmentIdService idService;
    @Autowired
    public CampusServiceImpl(CampusDao campusDao, CampusGroupDao campusGroupDao, ApartmentIdService idService) {
        this.campusDao = campusDao;
        this.campusGroupDao = campusGroupDao;
        this.idService = idService;
    }

    @Override
    public void addNewCampus(String campusName, String description) {
        campusDao.insertCampus(campusName, description);
    }

    /**
     * 高并发下可能会出现错误
     *
     * @param campusGroupName campusGroupName
     * @param campusName      campusName
     * @param description     description
     */
    @Override
    public void addNewCampusGroup(String campusGroupName, String campusName, InGender inGender, String description) {

        campusGroupDao.insertCampusGroup(idService.getNextCampusGroupId(), campusName, campusGroupName,inGender, description);
    }

    @Override
    public void deleteCampus(String campusName) {

        campusDao.deleteCampus(campusName);

    }

    @Override
    public void deleteCampusGroup(String campusGroupName) {

        campusGroupDao.deleteCampusGroupByCampusGroupName(campusGroupName);
    }

    @Override
    public List<Campus> queryAllCampus() {
        return campusDao.queryAllCampus();
    }

    @Override
    public List<CampusGroup> queryAllCampusGroup() {




        return campusGroupDao.queryAllCampusGroup();

    }

}
