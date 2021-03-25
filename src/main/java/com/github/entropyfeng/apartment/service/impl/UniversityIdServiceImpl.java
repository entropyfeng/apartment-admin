package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.service.UniversityIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class UniversityIdServiceImpl implements UniversityIdService {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public Integer getNextCollegeId() {

     return  Objects.requireNonNull(redisTemplate.opsForValue().increment(DEPARTMENT_FIELD_NAME)).intValue();

    }

    @Override
    public void clearAll() {
        ArrayList<String> toDeletes=new ArrayList<>();
        toDeletes.add(DEPARTMENT_FIELD_NAME);
        redisTemplate.delete(toDeletes);
    }
}
