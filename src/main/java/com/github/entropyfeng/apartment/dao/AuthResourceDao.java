package com.github.entropyfeng.apartment.dao;



import com.github.entropyfeng.apartment.domain.po.AuthResource;
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
public interface AuthResourceDao {

    /**
     * 插入新resource
     * @param resourceId resourceId
     * @param resourceName resourceName
     * @param method the http request method
     * @param path access path
     * @return 受影响的行数
     */
    Long insertResource(@Param("resourceId") Long resourceId, @Param("resourceName") String resourceName, @Param("method") String method, @Param("path") String path);

    /**
     * 根据id删除resource
     * @param resourceId resourceId
     * @return 受影响的行数
     */
    Long deleteResourceById(@Param("resourceId") Long resourceId);

    Long truncateAuthResource();

    List<AuthResource> queryResourceInRoleById(@Param("authRoleId")Long authRoleId);

    List<AuthResource> queryResourceInRoleByName(@Param("authRoleName")String authRoleName);
    List<String> queryResourceNamesInRoleByName(@Param("authRoleName")String authRoleName);

    Long queryResourceIdByName(@Param("authResourceName")String authResourceName);

    List<AuthResource> queryAllResources();

    List<AuthResource> queryAllResourcesByPage(PageRequest pageRequest);
}
