package com.github.entropyfeng.apartment;

import com.github.entropyfeng.apartment.dao.AuthResourceDao;
import com.github.entropyfeng.apartment.dao.AuthRoleDao;
import com.github.entropyfeng.apartment.dao.AuthUserDao;
import com.github.entropyfeng.apartment.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitAuthTest {

    private static final Logger logger= LoggerFactory.getLogger(InitAuthTest.class);
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

        authRoleService.addAuthRole("base");
        authRoleService.addAuthRole("administrator");
        authRoleService.addAuthRole("student");

        List<String> adminRoleNames=new ArrayList<>();
        adminRoleNames.add("administrator");
        adminRoleNames.add("base");
        authUserService.registerUser("admin", "admin", "11@$$.com", "mock0", false,adminRoleNames);

        long beforeCreateAccount= System.currentTimeMillis();
        List<String> studentRoleNames=new ArrayList<>();
        adminRoleNames.add("base");
        studentRoleNames.add("student");
        studentService.createAccountForAllNonAccountStudent(100,studentRoleNames);
        long afterCreateAccount=System.currentTimeMillis();
        logger.info("cause  {} second to create account ",(afterCreateAccount-beforeCreateAccount)/1000.0);

        //for auth
        authResourceService.addNewResource("acquireAllAuthUser", "GET", "/auth/user/all");
        authResourceService.addNewResource("acquireAllAuthRole", "GET", "/auth/role/all");
        authResourceService.addNewResource("acquireAllAuthResource", "GET", "/auth/resource/all");

        //for Base
        authResourceService.addNewResource("acquireCurrentUser", "GET", "/account/currentUser");
        authResourceService.addNewResource("resetMyPassword","PUT","/account/my/password");
        authResourceService.addNewResource("resetMyPhone","PUT","/account/my/phone");
        authResourceService.addNewResource("resetMyEmail","PUT","/account/my/email");

        //for apartment
        authResourceService.addNewResource("acquireAllBuilding", "GET", "/apartment/building/all");
        authResourceService.addNewResource("acquireCurrentBuildingNames", "GET", "/apartment/building/names");
        authResourceService.addNewResource("addSingleBuilding", "POST", "/apartment/building");
        authResourceService.addNewResource("deleteSingleBuilding", "DELETE", "/apartment/building");
        authResourceService.addNewResource("acquireAllCampus", "GET", "/apartment/campus/all");
        authResourceService.addNewResource("acquireCurrentCampusNames", "GET", "/apartment/campus/names");
        authResourceService.addNewResource("addSingleCampus", "POST", "/apartment/campus");
        authResourceService.addNewResource("deleteSingleCampus", "DELETE", "/apartment/campus");
        authResourceService.addNewResource("acquireCurrentCampusGroupNames", "GET", "/apartment/campusGroup/names");
        authResourceService.addNewResource("addSingleCampusGroup", "POST", "/apartment/campusGroup");
        authResourceService.addNewResource("addSingleDormitory", "POST", "/apartment/dormitory");
        authResourceService.addNewResource("deleteSingleCampusGroup", "DELETE", "/apartment/campusGroup");
        authResourceService.addNewResource("deleteSingleDormitory", "DELETE", "/apartment/dormitory");
        authResourceService.addNewResource("acquireAllCampusGroup", "GET", "/apartment/campusGroup/all");

        authResourceService.addNewResource("acquireAllDormitory","GET","/apartment/dormitory/all");
        authResourceService.addNewResource("acquireDormitoryByBuildingId","GET","/apartment/dormitory");
        authResourceService.addNewResource("acquireDetailDormitory","GET","/apartment/detail/dormitory");
        authResourceService.addNewResource("acquireMyDormitory","GET","/apartment/dormitory/my");
        authResourceService.addNewResource("acquireAvailableCampusNames","GET","/apartment/university/available/campus/names");
        authResourceService.addNewResource("acquireAvailableCampusGroupNames","GET","/apartment/university/available/campusGroup/names");
        authResourceService.addNewResource("acquireAvailableBuildingNames","GET","/apartment/university/available/building/names");
        authResourceService.addNewResource("acquireAvailableDormitory","GET","/apartment/university/available/dormitories");
        authResourceService.addNewResource("checkInMyDormitory","POST","/apartment/my/checkIn");
        authResourceService.addNewResource("checkOutMyDormitory","POST","/apartment/my/checkOut");
        authResourceService.addNewResource("acquireMyDormitoryStatus","GET","/apartment/my/status");
        //for university
        authResourceService.addNewResource("acquireAllStudent", "GET", "/university/student/all");
        authResourceService.addNewResource("acquireAllCollege","GET","/university/college/all");
        authResourceService.addNewResource("acquireCurrentCollegeNames","GET","/university/college/names");
        authResourceService.addNewResource("addSingleCollege","POST","/university/college");
        authResourceService.addNewResource("addSingleStudent","POST","/university/student");
        authResourceService.addNewResource("modifySingleStudent","PUT","/university/student");
        authResourceService.addNewResource("deleteSingleCollege","DELETE","/university/college");
        authResourceService.addNewResource("insertStudentsFromExcel","POST","/university/student/excel");
        authResourceService.addNewResource("deleteSingleStudent","DELETE","/university/student");
        authResourceService.addNewResource("downloadInsertStudentTemplate","GET","/university/student/excel/template");
        authResourceService.addNewResource("deleteSingleStudentAccount","DELETE","/university/student/account");
        authResourceService.addNewResource("createSingleStudentAccount","POST","/university/student/account");
        authResourceService.addNewResource("modifyStudentPassword","PUT","/university/student/password");



        authRoleService.grantResourceToRole("base","acquireCurrentUser");
        authRoleService.grantResourceToRole("base","resetMyPassword");
        authRoleService.grantResourceToRole("base","resetMyPhone");
        authRoleService.grantResourceToRole("base","resetMyEmail");


        authRoleService.grantResourceToRole("administrator", "acquireAllAuthUser");
        authRoleService.grantResourceToRole("administrator", "acquireAllAuthRole");
        authRoleService.grantResourceToRole("administrator", "acquireAllAuthResource");

        authRoleService.grantResourceToRole("administrator", "acquireCurrentUser");
        authRoleService.grantResourceToRole("administrator", "acquireAllBuilding");
        authRoleService.grantResourceToRole("administrator", "acquireAllCampus");
        authRoleService.grantResourceToRole("administrator", "acquireAllCampusGroup");

        authRoleService.grantResourceToRole("administrator", "addSingleCampus");
        authRoleService.grantResourceToRole("administrator", "addSingleCampusGroup");
        authRoleService.grantResourceToRole("administrator", "addSingleBuilding");
        authRoleService.grantResourceToRole("administrator", "addSingleDormitory");
        authRoleService.grantResourceToRole("administrator", "deleteSingleCampus");
        authRoleService.grantResourceToRole("administrator", "deleteSingleCampusGroup");
        authRoleService.grantResourceToRole("administrator", "deleteSingleBuilding");
        authRoleService.grantResourceToRole("administrator", "deleteSingleDormitory");
        authRoleService.grantResourceToRole("administrator", "acquireCurrentBuildingNames");
        authRoleService.grantResourceToRole("administrator", "acquireCurrentCampusNames");
        authRoleService.grantResourceToRole("administrator", "acquireCurrentCampusGroupNames");


        authRoleService.grantResourceToRole("administrator", "acquireAllStudent");
        authRoleService.grantResourceToRole("administrator", "acquireAllCollege");
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
        authRoleService.grantResourceToRole("administrator","deleteSingleStudent");
        authRoleService.grantResourceToRole("administrator","deleteSingleCollege");
        authRoleService.grantResourceToRole("administrator","addSingleCollege");
        authRoleService.grantResourceToRole("administrator","addSingleStudent");
        authRoleService.grantResourceToRole("administrator","modifySingleStudent");
        authRoleService.grantResourceToRole("administrator","acquireCurrentCollegeNames");
        authRoleService.grantResourceToRole("administrator","deleteSingleStudentAccount");
        authRoleService.grantResourceToRole("administrator","createSingleStudentAccount");
        authRoleService.grantResourceToRole("administrator","modifyStudentPassword");


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
