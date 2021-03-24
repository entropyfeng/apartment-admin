package com.github.entropyfeng.apartment.service.impl;


import com.github.entropyfeng.apartment.config.AuthProperties;
import com.github.entropyfeng.apartment.dao.AuthRoleDao;
import com.github.entropyfeng.apartment.dao.AuthUserDao;
import com.github.entropyfeng.apartment.domain.AccountStatus;
import com.github.entropyfeng.apartment.domain.po.AuthRole;
import com.github.entropyfeng.apartment.domain.po.AuthUser;
import com.github.entropyfeng.apartment.domain.to.PageRequest;
import com.github.entropyfeng.apartment.exception.AuthRoleNotExistException;
import com.github.entropyfeng.apartment.exception.AuthUserNotExistException;
import com.github.entropyfeng.apartment.exception.DuplicateUsernameException;
import com.github.entropyfeng.apartment.exception.PasswordErrorException;
import com.github.entropyfeng.apartment.service.AuthIdService;
import com.github.entropyfeng.apartment.service.AuthUserService;
import com.github.entropyfeng.apartment.util.PasswordValidator;
import com.github.entropyfeng.apartment.util.PostHandlePassword;
import com.github.entropyfeng.common.util.JWTUtil;
import com.github.entropyfeng.common.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author entropyfeng
 */
@Validated
@Service
public class AuthUserServiceImpl implements AuthUserService {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserServiceImpl.class);

    @Autowired
    public AuthUserServiceImpl(PasswordValidator passwordValidator, AuthProperties authProperties, AuthIdService authIdService, AuthUserDao authUserDao, AuthRoleDao authRoleDao) {
        this.authUserDao = authUserDao;
        this.authRoleDao = authRoleDao;
        this.authProperties = authProperties;
        this.authIdService = authIdService;
        this.passwordValidator = passwordValidator;
    }

    private final AuthProperties authProperties;

    private final AuthUserDao authUserDao;

    private final AuthIdService authIdService;

    private final AuthRoleDao authRoleDao;

    private final PasswordValidator passwordValidator;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void registerUser(@NotNull String authUsername, @NotNull String authPassword, String email, String phone, boolean enablePasswordCheck) {


        if (enablePasswordCheck) {
            passwordValidator.validatePassword(authPassword);
        }
        Boolean exist = authUserDao.queryAuthUserExistByAuthUsername(authUsername);

        if (exist) {
            throw new DuplicateUsernameException(authUsername);
        }

        authPassword = PostHandlePassword.encryptPassword(authPassword);
        Long authUserId=authIdService.getNextAuthUserId();
        authUserDao.insertBaseAuthUser(authUserId,authUsername,authPassword,email,phone, AccountStatus.COMMON);
    }



    @Override
    public void grantRoleToUser(String authUserName, String authRoleName)  {
        Long userId = authUserDao.queryAuthUserIdByUsername(authUserName);
        Long roleId;

        if (userId != null) {
            roleId = authRoleDao.queryRoleIdByName(authRoleName);
            if (roleId != null) {
                //有可能破坏联合unique index
                authUserDao.grantRoleToUser(userId, roleId);
            } else {
                throw new AuthRoleNotExistException();
            }
        } else {
            throw new AuthUserNotExistException();
        }

    }

    @Override
    public List<String> acquireRoleNamesInUser(String authUsername) throws AuthUserNotExistException {
        AuthUser authUser = authUserDao.queryAuthUserByAuthUsername(authUsername);
        if (authUser == null) {
            throw new AuthUserNotExistException();
        }
        return authRoleDao.queryRoleNameInUserByName(authUsername);

    }

    @Override
    public List<Long> acquireRoleIdsInUser(String authUsername) throws AuthUserNotExistException {
        AuthUser authUser = authUserDao.queryAuthUserByAuthUsername(authUsername);
        if (authUser == null) {
            throw new AuthUserNotExistException();
        }

        return authRoleDao.queryRoleIdInUserByName(authUsername);
    }

    @Override
    public List<Long> acquireRoleIdsInUser(Long authUserId) throws AuthUserNotExistException {
        AuthUser authUser = authUserDao.queryAuthUserByAuthUserId(authUserId);
        if (authUser == null) {
            throw new AuthUserNotExistException();
        }
        return authRoleDao.queryRoleIdInUserById(authUserId);
    }

    @Override
    public String userLogin(@NotNull String authUsername, @NotNull String authPassword)  {
        AuthUser authUser = authUserDao.queryAuthUserByAuthUsername(authUsername);
        if (authUser == null) {
            throw new AuthUserNotExistException();
        }

        if (authUser.getAuthPassword()==null||!authPassword.equals(authUser.getAuthPassword())) {
            throw new PasswordErrorException();
        }
        Long userId = authUser.getAuthUserId();
        List<AuthRole> roleList = authRoleDao.queryRoleInUserById(userId);
        List<String> roleNameList = roleList.stream().map(AuthRole::getAuthRoleName).collect(Collectors.toList());
        String jsonRoles = JsonUtil.stringListToJsonString(roleNameList);

        return JWTUtil.issueJwt(authProperties.jwtSecretKey, authIdService.getNextTokenId(), Long.toString(userId), authUsername, authProperties.issuer, "", authProperties.timeUnit, authProperties.duration, jsonRoles, "[]", SignatureAlgorithm.HS384);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean batchUpdateRolesInUser(Long authUserId, @NonNull List<Long> deleteIds, List<Long> createIds) {

        if (!deleteIds.isEmpty()) {
            authRoleDao.deleteBatchRolesInUser(authUserId, deleteIds);
        }

        if (!createIds.isEmpty()) {
            authRoleDao.createBatchRolesInUser(authUserId, createIds);
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteUsers(Long currentUserId, List<Long> toDeleteUserIds) {
        //检查该用户是否有删除相关用户的权限，此处默认存在
        if (toDeleteUserIds != null && !toDeleteUserIds.isEmpty()) {
            try {
                authUserDao.deleteBatchUsers(toDeleteUserIds);
            } catch (Exception e) {

                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteUsers(List<String> toDeleteUserNames) {

        if (toDeleteUserNames.isEmpty()){
            return;
        }
        authUserDao.deleteBatchUsersByUsernames(toDeleteUserNames);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSingleUser(Long currentUserId, Long toDeleteUserId) {

        authUserDao.deleteSingleUser(toDeleteUserId);
        authUserDao.deleteAuthUserByAuthUserId(toDeleteUserId);
    }

    @Override
    public void deleteSingleUser(String toDeleteUsername) {
        authUserDao.deleteSingleUserByUsername(toDeleteUsername);
        authRoleDao.deleteRelativeRoleByName(toDeleteUsername);
    }

    @Override
    public void resetPassword(@NotNull String authUsername, @NotNull String newPassword, boolean enablePasswordValidate) {
        if (enablePasswordValidate) {
            passwordValidator.validatePassword(newPassword);
        }
        AuthUser authUser = authUserDao.queryAuthUserByAuthUsername(authUsername);


        if (authUser == null) {
            logger.warn("username {} not exist in reset password",authUsername);
            throw new AuthUserNotExistException(authUsername);
        }

        if (logger.isInfoEnabled()){
            logger.info("username {} reset password",authUsername);
        }
        authUserDao.updatePasswordByName(authUsername, newPassword);

    }

    @Override
    public void resetPassword(@NotNull String authUsername, @NotNull String authPassword, @NotNull String newPassword, boolean enablePasswordValidate)  {

        if (enablePasswordValidate) {
            passwordValidator.validatePassword(newPassword);
        }
        AuthUser authUser = authUserDao.queryAuthUserByAuthUsername(authUsername);
        if (authUser == null) {
            logger.warn("username {} not exist in reset password",authUsername);
            throw new AuthUserNotExistException(authUsername);
        }
        String dataBasePassword=  PostHandlePassword.encryptPassword(authPassword);
        if (!dataBasePassword.equals(authUser.getAuthPassword())){
            throw new PasswordErrorException(authUsername);
        }

        if (logger.isInfoEnabled()){
            logger.info("username {} reset password",authUsername);
        }
        authUserDao.updatePasswordByName(authUsername, newPassword);
    }



    @Override
    public AuthUser getAuthUserByName(String authUsername) throws AuthUserNotExistException {
        AuthUser authUser = authUserDao.queryAuthUserByAuthUsername(authUsername);

        if (authUser == null) {
            throw new AuthUserNotExistException();
        }
        return authUser;
    }

    @Override
    public AuthUser getAuthUserById(Long authUserId) throws AuthUserNotExistException {
        AuthUser authUser = authUserDao.queryAuthUserByAuthUserId(authUserId);
        if (authUser == null) {
            throw new AuthUserNotExistException();
        }
        return authUser;

    }

    @Override
    public Long getAuthUserIdByName(String authUserName) throws AuthUserNotExistException {
        Long res = authUserDao.queryAuthUserIdByUsername(authUserName);
        if (res == null) {
            throw new AuthUserNotExistException();
        }
        return res;
    }

    @Override
    public List<AuthUser> allAuthUser() {
        return authUserDao.queryAllAuthUser();
    }

    @Override
    public List<AuthUser> acquireAuthUserListByNames(List<String> usernames ) {

        if (usernames.isEmpty()){
            return new ArrayList<>();
        }
        return authUserDao.queryAuthUserByUsernames(usernames);
    }


    @Override
    public Boolean updateAuthUser(AuthUser authUser) {


        return 1 == authUserDao.updateByAuthUserIdSelective(authUser);
    }

    @Override
    public PageInfo<AuthUser> allAuthUserByPages(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        return new PageInfo<>(authUserDao.queryAllUsersByPage(pageRequest));

    }

    @Override
    public String acquireAuthUserNameById(Integer authUserId) {
        return authUserDao.queryAuthUserNameById(authUserId);
    }



}
