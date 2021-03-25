package com.github.entropyfeng.apartment;

import com.github.entropyfeng.apartment.dao.AuthResourceDao;
import com.github.entropyfeng.apartment.dao.AuthRoleDao;
import com.github.entropyfeng.apartment.dao.AuthUserDao;
import com.github.entropyfeng.apartment.domain.po.AuthResource;
import com.github.entropyfeng.apartment.domain.po.AuthUser;
import com.github.entropyfeng.apartment.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitAuthTest {

    AtomicInteger atomicInteger = new AtomicInteger(1);
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
    AuthResourceService authResourceService;

    @Autowired
    StudentService studentService;

    @Autowired
    AuthIdService authIdService;

    private void clearAll() {
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
    public void test() {

        clearAll();
        authUserService.registerUser("admin", "admin", "11@$$.com", "mock0", false);


        studentService.createAccountForAllNonAccountStudent();


        authRoleService.addAuthRole("administrator");
        authRoleService.addAuthRole("student");
        List<String> studentIdList= authUserDao.queryAllAuthUser().stream().map(AuthUser::getAuthUsername).collect(Collectors.toList());
        studentIdList.forEach(s -> authUserService.grantRoleToUser(s, "student"));

        authUserService.grantRoleToUser("admin", "administrator");

        authResourceService.addNewResource("acquireCurrentUser", "GET", "/account/currentUser");
        authResourceService.addNewResource("acquireAllBuilding", "GET", "/apartment/building/all");
        authResourceService.addNewResource("acquireAllCampus", "GET", "/apartment/campus/all");
        authResourceService.addNewResource("acquireAllCampusGroup", "GET", "/apartment/campusGroup/all");
        authResourceService.addNewResource("acquireAllUser", "GET", "/auth/user/all");
        authResourceService.addNewResource("acquireAllStudent", "GET", "/university/student/all");


        authRoleService.grantResourceToRole("administrator", "acquireCurrentUser");
        authRoleService.grantResourceToRole("administrator", "acquireAllBuilding");
        authRoleService.grantResourceToRole("administrator", "acquireAllCampus");
        authRoleService.grantResourceToRole("administrator", "acquireAllCampusGroup");
        authRoleService.grantResourceToRole("administrator", "acquireAllUser");
        authRoleService.grantResourceToRole("administrator", "acquireAllStudent");

        authRoleService.grantResourceToRole("student", "acquireCurrentUser");

    }

}
