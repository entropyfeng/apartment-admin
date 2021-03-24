package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.service.AuthIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.github.entropyfeng.apartment.config.AuthConst.*;


/**
 * 在分布式系统下仍能保持id一致性
 * @author entropyfeng
 *
 */
@Service
public class AuthIdServiceImpl implements AuthIdService {

    @Autowired
    public AuthIdServiceImpl(StringRedisTemplate redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    private final StringRedisTemplate redisTemplate;

    @Override
    public Long getNextAuthUserId() {

        return redisTemplate.opsForValue().increment(AUTH_USER_ID);
    }

    @Override
    public Long getNextAuthRoleId() {
        return redisTemplate.opsForValue().increment(AUTH_ROLE_ID);
    }

    @Override
    public Long getNextAuthResourceId() {
        return redisTemplate.opsForValue().increment(AUTH_RESOURCE_ID);
    }

    @Override
    public void clearAuthUserId() {

        redisTemplate.delete(AUTH_USER_ID);
    }

    @Override
    public void clearAuthRoleId() {

        redisTemplate.delete(AUTH_ROLE_ID);
    }

    @Override
    public void clearAuthResourceId() {

        redisTemplate.delete(AUTH_RESOURCE_ID);
    }

    @Override
    public String getNextTokenId() {

        return UUID.randomUUID().toString();
    }
}
