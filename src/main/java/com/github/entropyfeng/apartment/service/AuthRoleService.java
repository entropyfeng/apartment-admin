package com.github.entropyfeng.apartment.service;



import com.github.entropyfeng.apartment.domain.po.AuthResource;
import com.github.entropyfeng.apartment.domain.po.AuthRole;
import com.github.entropyfeng.apartment.domain.to.PageRequest;
import com.github.entropyfeng.apartment.exception.AuthResourceNotExistException;
import com.github.entropyfeng.apartment.exception.AuthRoleNotExistException;
import com.github.pagehelper.PageInfo;

import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author entropyfeng
 */
public interface AuthRoleService {

    /**
     * 向某角色添加权限
     * @param authRoleName 角色名
     * @param authResourceName 权限名
     */
     void grantResourceToRole(String authRoleName, String authResourceName);

    /**
     *
     * 剥夺某角色的权限
     * @param authRoleName 角色名
     * @param authResourceName 权限名
     * @throws AuthRoleNotExistException 不存在该角色
     * @throws AuthResourceNotExistException 不存在该资源
     */
    void depriveResourceFromRole(String authRoleName,String authResourceName);

    /**
     *
     * 获取所有角色
     * @return {@link List< AuthRole >}
     */
    @NonNull List<AuthRole> allRoles();

    /**
     * 分页获取所有角色
     * @param pageRequest 请求
     * @return 与AuthRole相关的分页信息
     */
    @NonNull
    PageInfo<AuthRole> allRolesByPage(PageRequest pageRequest);




    /**
     * 根据角色名批量删除角色
     * @param authRoleNames 角色名
     */
    void deleteAuthRole(List<String> authRoleNames);


    /**
     * 查询角色所包含的资源
     * @param roleName 角色名
     * @return 资源集合
     */
    List<AuthResource> searchResourcesInRole(String roleName);

    /**
     * 查询角色所包含的资源名
     * @param roleName 角色名
     * @return 资源名集合
     */
    List<String> searchResourceNamesInRole(String roleName);

    /**
     *
     * 获取该角色所有resource
     * @return {@link List<AuthResource>}
     */
    @NonNull List<AuthResource> searchResourcesInRole(Long roleId);
}
