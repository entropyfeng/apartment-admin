package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.dao.AuthResourceDao;
import com.github.entropyfeng.apartment.dao.AuthRoleDao;
import com.github.entropyfeng.apartment.domain.po.AuthResource;
import com.github.entropyfeng.apartment.domain.po.AuthRole;
import com.github.entropyfeng.apartment.domain.to.PageRequest;
import com.github.entropyfeng.apartment.exception.AuthResourceNotExistException;
import com.github.entropyfeng.apartment.exception.AuthRoleNotExistException;
import com.github.entropyfeng.apartment.service.AuthIdService;
import com.github.entropyfeng.apartment.service.AuthRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author entropyfeng
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    private static final Logger logger = LoggerFactory.getLogger(AuthRoleServiceImpl.class);

    private final AuthRoleDao authRoleDao;

    private final AuthResourceDao authResourceDao;

    private final AuthIdService authIdService;
    @Autowired
    public AuthRoleServiceImpl(AuthIdService authIdService,AuthRoleDao authRoleDao, AuthResourceDao authResourceDao) {
        this.authRoleDao = authRoleDao;
        this.authResourceDao = authResourceDao;
        this.authIdService=authIdService;
    }

    @Override
    public void grantResourceToRole(String authRoleName, String authResourceName) {

        Long roleId = authRoleDao.queryRoleIdByName(authRoleName);
        Long resourceId;
        if (roleId == null) {
            logger.error("role {} not exist",authRoleName);
            throw new AuthRoleNotExistException(String.format("role %s not exist", authRoleName));
        }

        resourceId = authResourceDao.queryResourceIdByName(authResourceName);
        if (resourceId == null) {
            logger.error("resource {} not exist", authResourceName);
            throw new AuthResourceNotExistException(String.format("resource %s not exist", authResourceName));
        }

        //有可能破坏联合unique index
        authRoleDao.insertResourceIntoRole(roleId, resourceId);


    }

    @Override
    public void addAuthRole(String roleName) {
        Long authRoleId=authIdService.getNextAuthRoleId();
        authRoleDao.insertAuthRole(authRoleId,roleName);
    }

    @Override
    public void depriveResourceFromRole(String authRoleName, String authResourceName) throws AuthRoleNotExistException, AuthResourceNotExistException {

    }


    @Override
    public List<AuthRole> allRoles() {

        return authRoleDao.queryAllRoles();
    }

    @Override
    public PageInfo<AuthRole> allRolesByPage(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        return new PageInfo<>(authRoleDao.queryAllRolesByPage(pageRequest));
    }

    @Override
    public List<AuthResource> searchResourcesInRole(Long roleId) {
        return authResourceDao.queryResourceInRoleById(roleId);
    }

    @Override
    public void deleteAuthRole(List<String> authRoleNames) {

        List<AuthRole> authRoles = authRoleDao.queryAuthRoles(authRoleNames);

        List<String> names = authRoles.stream().map(AuthRole::getAuthRoleName).collect(Collectors.toList());
        authRoleDao.deleteBatchAuthRole(names);

    }

    @Override
    public List<AuthResource> searchResourcesInRole(String roleName) {


        return authResourceDao.queryResourceInRoleByName(roleName);
    }

    @Override
    public List<String> searchResourceNamesInRole(String roleName) {
        return authResourceDao.queryResourceNamesInRoleByName(roleName);
    }


}
