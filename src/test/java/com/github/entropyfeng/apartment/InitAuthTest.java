package com.github.entropyfeng.apartment;

import com.github.entropyfeng.apartment.dao.AuthResourceDao;
import com.github.entropyfeng.apartment.dao.AuthRoleDao;
import com.github.entropyfeng.apartment.dao.AuthUserDao;
import com.github.entropyfeng.apartment.service.AuthIdService;
import com.github.entropyfeng.apartment.service.AuthRoleService;
import com.github.entropyfeng.apartment.service.AuthUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitAuthTest {

    @Autowired
    AuthUserDao authUserDao;

    @Autowired
    AuthRoleDao authRoleDao;
    @Autowired
    AuthResourceDao authResourceDao;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    AuthRoleService authRoleService;

    @Autowired
    AuthIdService authIdService;

    private void clearAll(){
        authUserDao.truncateAuthUser();
        authUserDao.truncateAuthUserRole();
        authRoleDao.truncateAuthRole();
        authRoleDao.truncateAuthRoleResource();
        authResourceDao.truncateAuthResource();
        authIdService.clearAuthUserId();
        authIdService.clearAuthResourceId();
        authIdService.clearAuthRoleId();
    }

    @Test
    public void test(){

        clearAll();
        authUserService.registerUser("admin","admin","11@$$.com","mock0",false);

        authRoleService.addAuthRole("administrator");
        authRoleService.addAuthRole("student");


    }

}
