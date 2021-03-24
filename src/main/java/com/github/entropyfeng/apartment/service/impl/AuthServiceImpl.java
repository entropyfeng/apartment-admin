package com.github.entropyfeng.apartment.service.impl;


import com.github.entropyfeng.apartment.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * @author entropyfeng
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    public AuthServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;

    }

    private final StringRedisTemplate redisTemplate;




    @Override
    public boolean checkPerm(@NonNull List<String> roleList, String method, String path) {

        return roleList.stream().anyMatch(roleName-> redisTemplate.opsForSet().isMember(roleName,method+path));

    }


}
