package com.github.entropyfeng.apartment.service;



import com.github.entropyfeng.apartment.domain.po.AuthResource;

import com.github.entropyfeng.apartment.domain.to.PageRequest;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author entropyfeng
 */
public interface AuthResourceService {
    /**
     * 获取所有资源
     * @return {@link List <AuthResource>}
     */
    @NonNull
    List<AuthResource> allResource();



    @NonNull
    PageInfo<AuthResource> allResourceByPage(PageRequest pageRequest);
}
