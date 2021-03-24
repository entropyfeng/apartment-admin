package com.github.entropyfeng.apartment.dao;



import com.github.entropyfeng.apartment.domain.AccountStatus;
import com.github.entropyfeng.apartment.domain.po.AuthRole;
import com.github.entropyfeng.apartment.domain.po.AuthUser;
import com.github.entropyfeng.apartment.domain.to.PageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author entropyfeng
 */
@Repository
@Mapper
public interface AuthUserDao {

    /**
     * 创建新用户
     * @param authUserId 用户id
     * @param authUsername 用户名
     * @param authUserPassword 密码
     */
    Long insertAuthUser(@Param("authUserId") Long authUserId, @Param("authUsername") String authUsername, @Param("authUserPassword") String authUserPassword);

    Long idInsertAuthUserWithAccountStatus(@Param("authUserId") Long authUserId, @Param("authUsername") String authUsername, @Param("authUserPassword") String authUserPassword, @Param("status")AccountStatus status);

    Boolean queryAuthUserExistByAuthUsername(@Param("authUsername")String authUsername);

    AuthUser queryAuthUserByAuthUsername(@Param("authUsername")String authUsername);

    String queryPasswordByAuthUsername(@Param("authUsername")String authUsername);

    Long queryAuthUserIdByUsername(@Param("authUsername")String authUsername);

    String queryAuthUserNameById(@Param("authUserId")Integer authUserId);
    AuthUser queryAuthUserByAuthUserId(@Param("authUserId") Long authUserId);

    AuthUser queryAuthUserByEmail(@Param("email") String email);

    AuthUser queryAuthUserByPhone(@Param("phone") String phone);

    Long deleteAuthUserByAuthUserId(@Param("authUserId") Long authUserId);

    /**
     * 查询所有用户
     * @return {@link AuthUser}集合
     */
    List<AuthUser> queryAllAuthUser();

    /**
     * 添加{@link AuthUser}与{@link AuthRole}的关系
     * @param authUserId authUserId {@link AuthUser}
     * @param authRoleId authRoleId {@link AuthRole}
     * @return 受影响的行数
     */
    Long grantRoleToUser(@Param("authUserId") Long authUserId, @Param("authRoleId")Long authRoleId);

    /**
     * 删除{@link AuthUser}与{@link AuthRole}的关系
     * @param authUserId authUserId {@link AuthUser}
     * @param authRoleId authRoleId {@link AuthRole}
     * @return 受影响的行数
     */
    Long deleteRelationBetweenRoleAndUser(@Param("authUserId") Long authUserId,@Param("authRoleId")Long authRoleId);
    Long deleteBatchUsers(@Param("list") List<Long> userIdList);
    Long deleteBatchUsersByUsernames(@Param("list")List<String> list);
    Long deleteSingleUser(@Param("authUserId")Long authUserId);
    Long deleteSingleUserByUsername(@Param("authUsername")String authUsername);

    List<String> queryUsernameListByUsername(@Param("list")List<String> list);
    /**
     * 清空auth_user 表并保留结构
     */
    void truncateAuthUser();

    /**
     * 清空auth_user_role 表并保留结构
     */
    void truncateAuthUserRole();


    void updatePasswordByName(@Param("authUsername")String authUsername,@Param("authPassword") String authPassword);


    List<AuthUser> queryAllUsersByPage(PageRequest pageRequest);

    List<AuthUser> queryAuthUserByUsernames(@Param("list") List<String> list);

    int updateEmailOrPhone(@Param("authUserName")String authUserName,@Param("email") String email,@Param("phone") String phone);

    int updateByAuthUserIdSelective(@Param("authUser") AuthUser authUser);

    int insertBaseAuthUser(@Param("authUserId")Long authUserId,@Param("authUserName")String authUserName,@Param("authPassword") String authPassword,@Param("email") String email,@Param("phone") String phone,@Param("status") AccountStatus accountStatus);
}
