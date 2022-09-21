package com.github.entropyfeng.apartment.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.entropyfeng.apartment.config.cache.CollegeCache;
import com.github.entropyfeng.apartment.dao.StudentDao;
import com.github.entropyfeng.apartment.domain.AccountStatus;
import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.StudentStatus;
import com.github.entropyfeng.apartment.domain.excel.StudentExcel;
import com.github.entropyfeng.apartment.domain.excel.StudentExcelListener;
import com.github.entropyfeng.apartment.domain.po.Student;
import com.github.entropyfeng.apartment.domain.to.RegisterUserTo;
import com.github.entropyfeng.apartment.domain.to.StudentTo;
import com.github.entropyfeng.apartment.domain.vo.SimpleStudentInfo;
import com.github.entropyfeng.apartment.domain.vo.StudentVO;
import com.github.entropyfeng.apartment.service.AuthUserService;
import com.github.entropyfeng.apartment.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    final
    StudentDao studentDao;

    final
    CollegeCache collegeCache;
    final
    AuthUserService authUserService;

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, CollegeCache collegeCache, AuthUserService authUserService) {
        this.studentDao = studentDao;
        this.collegeCache = collegeCache;
        this.authUserService = authUserService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createAccountForAllNonAccountStudent(int limit, List<String> roleNames) {

        List<Student> students = studentDao.queryAllStudents();
        createAccount(students, roleNames, limit);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createAccountForNonAccountStudent(List<String> studentIds, List<String> roleNames) {

        List<Student> students = studentDao.queryStudentsByStudentIds(studentIds);
        createAccount(students, roleNames, 0);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createAccountForSingleStudent(String studentId) {


        List<String> roleList = new ArrayList<>();
        roleList.add("base");
        roleList.add("student");
        Student student = studentDao.queryStudentByStudentId(studentId);
        RegisterUserTo registerUserTo = new RegisterUserTo();
        registerUserTo.setPassword(studentId);
        registerUserTo.setUsername(studentId);
        registerUserTo.setPhone(student.getPhone());
        registerUserTo.setEmail(student.getEmail());
        authUserService.registerUser(registerUserTo, roleList);
        studentDao.updateAccountStatus(studentId, AccountStatus.EXIST);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyStudentPassword(String studentId, String newPassword) {
        authUserService.resetPassword(studentId,newPassword,false);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAccountForSingleStudent(String studentId) {

        authUserService.deleteSingleUser(studentId);

    }

    @Transactional(rollbackFor = Exception.class)
    public void createAccount(List<Student> students, List<String> roleNames, int limit) {
        List<Student> noAccountStudents = students.stream().filter(student -> student.getAccountStatus().equals(AccountStatus.NOT_EXIST)).collect(Collectors.toList());
        if (limit > 0) {
            noAccountStudents = noAccountStudents.stream().limit(limit).collect(Collectors.toList());
        }
        List<RegisterUserTo> registerUserTos = noAccountStudents.stream().map(student -> {
            RegisterUserTo registerUserTo = new RegisterUserTo();
            registerUserTo.setEmail(student.getEmail());
            registerUserTo.setPhone(student.getPhone());
            registerUserTo.setUsername(student.getStudentId());
            registerUserTo.setPassword(student.getStudentId());
            return registerUserTo;
        }).collect(Collectors.toList());

        authUserService.batchRegisterUser(registerUserTos, roleNames);

        noAccountStudents.forEach(student -> {
            studentDao.updateAccountStatus(student.getStudentId(), AccountStatus.EXIST);
        });

    }

    @Override
    public void insertStudentByExcel(MultipartFile file) throws IOException {

        List<StudentTo> studentTos = new ArrayList<>();
        StudentExcelListener listener = new StudentExcelListener(studentTos);


        EasyExcel.read(file.getInputStream(), StudentExcel.class,listener).sheet().doRead();
        if (logger.isInfoEnabled()){

            ObjectMapper objectMapper=new ObjectMapper();
            String res= objectMapper.writeValueAsString(studentTos.stream().map(StudentTo::getStudentId).collect(Collectors.toList()));
            logger.info("attempt insert student by excel {}",res);

        }

        insertBatchStudent(studentTos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBatchStudent(List<StudentTo> studentList) {
        studentList.forEach(this::insertStudent);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertStudent(StudentTo studentTo) {
        Student student = new Student();
        student.setStudentId(studentTo.getStudentId());
        student.setStudentName(studentTo.getStudentName());
        student.setIdCardNumber(studentTo.getIdCardNumber());
        student.setCollegeId(collegeCache.getId(studentTo.getCollegeName()));
        student.setPhone(studentTo.getPhone());
        student.setEmail(studentTo.getEmail());
        student.setRegisterYear(studentTo.getRegisterYear());
        student.setGender(Gender.toInGender(studentTo.getGender()));
        student.setAccountStatus(AccountStatus.NOT_EXIST);
        student.setStudentStatus(StudentStatus.ADMITTED_NOT_REGISTERED);
        studentDao.insertStudent(student);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSingleStudent(String studentId) {
        studentDao.deleteStudentByStudentId(studentId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStudent(StudentTo studentTo) {


        String studentId = studentTo.getStudentId();
        Student student = studentDao.queryStudentByStudentId(studentId);

        if (student != null) {
            String email = studentTo.getEmail();
            String phone = studentTo.getPhone();
            String idCardNumber = studentTo.getIdCardNumber();

            if (email != null) {
                student.setEmail(email);
            }
            if (phone != null) {
                student.setPhone(phone);
            }
            if (idCardNumber != null) {
                student.setIdCardNumber(idCardNumber);
            }

            studentDao.updateByStudentIdSelective(student);
        }


    }

    @Override
    public List<SimpleStudentInfo> querySimpleStudentInfoList(List<String> studentIdList) {

        return   studentDao.querySimpleStudentInfoList(studentIdList);
    }

    @Override
    public List<SimpleStudentInfo> querySimpleStudentInfoByConditionAnd(String studentName,String studentId, String collegeName, String registerYear, Gender gender) {
        return studentDao.querySimpleStudentAnd(studentName,studentId, collegeName, gender, registerYear);
    }

    @Override
    public List<SimpleStudentInfo> querySimpleStudentInfoByConditionOr(String studentName,String studentId, String collegeName, String registerYear, Gender gender) {
        return studentDao.querySimpleStudentOr(studentName,studentId, collegeName, gender, registerYear);
    }


    @Override
    public List<StudentVO> queryStudents() {
        return studentDao.queryAllStudents().stream().map(student -> new StudentVO(student, collegeCache.getName(student.getCollegeId()))).collect(Collectors.toList());

    }

    @Override
    public StudentVO querySingleStudent(String type, String studentId, String phone, String email, String idCardNumber) {
        Student student=null;
        if(StringUtils.isEmpty(type)){

            if (!StringUtils.isEmpty(studentId)){
               student= studentDao.queryStudentByStudentId(studentId);
            }else if (!StringUtils.isEmpty(phone)){
                student=studentDao.queryStudentByPhone(phone);
            }else if(!StringUtils.isEmpty(idCardNumber)){
                student=studentDao.queryStudentByIdCardNumber(idCardNumber);
            }
        }else {
            switch (type){
                case "studentId":student=studentDao.queryStudentByStudentId(studentId);break;
                case "phone":student=studentDao.queryStudentByPhone(phone);break;
                case "idCardNumber":student=studentDao.queryStudentByIdCardNumber(idCardNumber);break;
                default:break;
            }
        }
        if (student!=null){
            return new StudentVO(student,collegeCache.getName(student.getCollegeId()));
        }

        return null;
    }
}
