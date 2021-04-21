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

     return  Objects.requireNonNull(redisTemplate.opsForValue().increment(COLLEGE_FIELD_NAME)).intValue();

    }


    private void clearCollegeId(){
        redisTemplate.delete(COLLEGE_FIELD_NAME);
    }
    @Override
    public void clearAll() {
        clearCollegeId();
    }
}
