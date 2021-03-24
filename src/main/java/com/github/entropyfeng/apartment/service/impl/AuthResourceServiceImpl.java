package com.github.entropyfeng.apartment.service.impl;



import com.github.entropyfeng.apartment.dao.AuthResourceDao;
import com.github.entropyfeng.apartment.domain.po.AuthResource;
import com.github.entropyfeng.apartment.domain.to.PageRequest;
import com.github.entropyfeng.apartment.service.AuthResourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author entropyfeng
 */
@Service
public class AuthResourceServiceImpl implements AuthResourceService {

    @Autowired
    public AuthResourceServiceImpl(AuthResourceDao authResourceDao) {
        this.authResourceDao = authResourceDao;
    }

    private AuthResourceDao authResourceDao;
    @Override
    public List<AuthResource> allResource() {
        return authResourceDao.queryAllResources();
    }

    @Override
    public PageInfo<AuthResource> allResourceByPage(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(),pageRequest.getPageSize());

        return new PageInfo<>(authResourceDao.queryAllResourcesByPage(pageRequest)) ;
    }
}
