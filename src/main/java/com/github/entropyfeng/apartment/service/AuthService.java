package com.github.entropyfeng.apartment.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author entropyfeng
 */
@Service
public interface AuthService {

    /**
     * 从Redis中检查该token中的RoleList是否具有相应的权限
     * @param roleList 角色集合
     * @param method  {@link org.springframework.web.bind.annotation.GetMapping}
     *       or {@link org.springframework.web.bind.annotation.PutMapping}
     *       or ... the method of request
     * @param path the path of url
     * @return false->验证失败
     *         true->验证成功
     */
    boolean checkPerm(@NonNull List<String> roleList, String method, String path);


}
