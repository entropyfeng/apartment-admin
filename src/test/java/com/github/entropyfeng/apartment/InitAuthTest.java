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

        long beforeCreateAccount= System.currentTimeMillis();
        studentService.createAccountForAllNonAccountStudent();
        long afterCreateAccount=System.currentTimeMillis();
        String xx= String.format("cause  %s second to create account ",(afterCreateAccount-beforeCreateAccount)/1000.0);
        System.out.println(xx);

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
        authResourceService.addNewResource("acquireAllDormitory","GET","/apartment/dormitory/all");
        authResourceService.addNewResource("insertStudentsFromExcel","POST","/university/student/excel");
        authResourceService.addNewResource("acquireDormitoryByBuildingId","GET","/apartment/dormitory");
        authResourceService.addNewResource("acquireDetailDormitory","GET","/apartment/detail/dormitory");
        authResourceService.addNewResource("acquireMyDormitory","GET","/apartment/dormitory/my");
        authResourceService.addNewResource("downloadInsertStudentTemplate","GET","/university/student/excel/template");
        authResourceService.addNewResource("acquireAvailableCampusNames","GET","/apartment/university/available/campus/names");
        authResourceService.addNewResource("acquireAvailableCampusGroupNames","GET","/apartment/university/available/campusGroup/names");
        authResourceService.addNewResource("acquireAvailableBuildingNames","GET","/apartment/university/available/building/names");
        authResourceService.addNewResource("acquireAvailableDormitory","GET","/apartment/university/available/dormitories");
        authResourceService.addNewResource("checkInMyDormitory","POST","/apartment/my/checkIn");
        authResourceService.addNewResource("checkOutMyDormitory","POST","/apartment/my/checkOut");
        authResourceService.addNewResource("acquireMyDormitoryStatus","GET","/apartment/my/status");


        authRoleService.grantResourceToRole("administrator", "acquireCurrentUser");
        authRoleService.grantResourceToRole("administrator", "acquireAllBuilding");
        authRoleService.grantResourceToRole("administrator", "acquireAllCampus");
        authRoleService.grantResourceToRole("administrator", "acquireAllCampusGroup");
        authRoleService.grantResourceToRole("administrator", "acquireAllUser");
        authRoleService.grantResourceToRole("administrator", "acquireAllStudent");
        authRoleService.grantResourceToRole("administrator", "acquireAllDormitory");
        authRoleService.grantResourceToRole("administrator", "insertStudentsFromExcel");
        authRoleService.grantResourceToRole("administrator", "acquireDetailDormitory");
        authRoleService.grantResourceToRole("administrator","acquireMyDormitory");
        authRoleService.grantResourceToRole("administrator", "acquireDormitoryByBuildingId");
        authRoleService.grantResourceToRole("administrator","downloadInsertStudentTemplate");
        authRoleService.grantResourceToRole("administrator","acquireAvailableCampusNames");
        authRoleService.grantResourceToRole("administrator","acquireAvailableCampusGroupNames");
        authRoleService.grantResourceToRole("administrator","acquireAvailableBuildingNames");
        authRoleService.grantResourceToRole("administrator","acquireAvailableDormitory");


        authRoleService.grantResourceToRole("student", "acquireCurrentUser");
        authRoleService.grantResourceToRole("student","acquireMyDormitory");
        authRoleService.grantResourceToRole("student","acquireAvailableCampusNames");
        authRoleService.grantResourceToRole("student","acquireAvailableCampusGroupNames");
        authRoleService.grantResourceToRole("student","acquireAvailableBuildingNames");
        authRoleService.grantResourceToRole("student","acquireAvailableDormitory");
        authRoleService.grantResourceToRole("student","acquireDetailDormitory");
        authRoleService.grantResourceToRole("student","checkInMyDormitory");
        authRoleService.grantResourceToRole("student","checkOutMyDormitory");
        authRoleService.grantResourceToRole("student","acquireMyDormitoryStatus");

    }


}
