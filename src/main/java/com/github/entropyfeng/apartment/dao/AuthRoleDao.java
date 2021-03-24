package com.github.entropyfeng.apartment.dao;


import com.github.entropyfeng.apartment.domain.po.AuthResource;
import com.github.entropyfeng.apartment.domain.po.AuthRole;
import com.github.entropyfeng.apartment.domain.to.PageRequest;
import com.github.entropyfeng.apartment.domain.to.RoleAndResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author entropyfeng
 */
@Repository
@Mapper
public interface AuthRoleDao {

    /**
     * 插入新角色
     * @param authRoleId roleId
     * @param authRoleName roleName
     * @return the number of row affected
     */
    Long insertAuthRole(@Param("authRoleId") Long authRoleId, @Param("authRoleName") String authRoleName);

    /**
     * 根据RoleId删除角色
     * @param authRoleId roleId
     * @return the number of row affected
     */
    Long deleteAuthRoleById(@Param("authRoleId")Long authRoleId);

    /**
     * 批量从用户中删除角色
     * @param authUserId 用户id
     * @param roleIdList 表示roleId的集合
     * @return the number of row affected
     */
    Long deleteBatchRolesInUser(@Param("authUserId")Long authUserId,@Param("list") List<Long> roleIdList);


    /**
     * 批量向用户中添加角色
     * @return the number of row affected
     */
  /*  Long createBatchRolesInUser(List<AuthUserRole> list);*/

    Long createBatchRolesInUser(@Param("authUserId") Long authUserId,@Param("list") List<Long> roleIds);

    /**
     *
     * 添加{@link AuthResource}与{@link AuthRole}的关系
     * @param authRoleId authRoleId {@link AuthRole}
     * @param authResourceId authResourceId{@link AuthResource}
     * @return 受影响的行数
     */
    Long insertResourceIntoRole(@Param("authRoleId") Long authRoleId,@Param("authResourceId")Long authResourceId);

    /**
     * 删除{@link AuthResource}与{@link AuthRole}的关系
     * @param authRoleId authRoleId {@link AuthRole}
     * @param authResourceId authResourceId{@link AuthResource}
     * @return 受影响的行数
     */
    Long deleteRelationBetweenRoleAndResource(@Param("authRoleId")Long authRoleId,@Param("authResourceId") Long authResourceId);

    /**
     * clear table auth_user
     * @return
     */
    Long truncateAuthRole();

    Long truncateAuthRoleResource();

    AuthRole queryRoleById(@Param("authRoleId")Long authRoleId);

    List<AuthRole> queryRoleInUserById(@Param("authUserId")Long authUserId);

    List<AuthRole> queryRoleInUserByName(@Param("authUserName")String authUserName);

    List<String> queryRoleNameInUserByName(@Param("authUserName")String authUserName);

    List<Long> queryRoleIdInUserByName(@Param("authUserName")String authUserName);

    List<Long> queryRoleIdInUserById(@Param("authUserId")Long authUserId);

    AuthRole queryRoleByName(@Param("authRoleName")String authRoleName);

    Long queryRoleIdByName(@Param("authRoleName")String authRoleName);

    List<RoleAndResource> queryRoleAndResource();

    List<AuthRole> queryAllRoles();

    List<AuthRole> queryAllRolesByPage(PageRequest pageRequest);

    List<AuthRole> queryAuthRoles(@Param("list") List<String> authRoleNames);

    Long deleteBatchAuthRole(@Param("list") List<String> authRoleNames);

    Long deleteRelativeRoleByName(@Param("authUserName") String authUserName);
    Long deleteRelativeRoleById(@Param("authUserId")Integer authUserId);

}
