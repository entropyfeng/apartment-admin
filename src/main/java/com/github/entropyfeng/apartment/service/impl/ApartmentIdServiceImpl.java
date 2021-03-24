package com.github.entropyfeng.apartment.service.impl;

import com.github.entropyfeng.apartment.service.ApartmentIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class ApartmentIdServiceImpl implements ApartmentIdService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public Integer getNextCampusId() {
        return Objects.requireNonNull(redisTemplate.opsForValue().increment(CAMPUS_ID_FIELD)).intValue();
    }

    @Override
    public Integer getNextCampusGroupId() {
      return  Objects.requireNonNull(redisTemplate.opsForValue().increment(CAMPUS_GROUP_ID_FIELD)).intValue();
    }

    @Override
    public Integer getNextBuildingId() {
        return Objects.requireNonNull(redisTemplate.opsForValue().increment(BUILDING_ID_FIELD)).intValue();
    }

    @Override
    public Integer getNextDormitoryId() {
        return Objects.requireNonNull(redisTemplate.opsForValue().increment(DORMITORY_ID_FIELD)).intValue();
    }

    @Override
    public void clearAllIds() {
        ArrayList<String> toDeletes=new ArrayList<>();
        toDeletes.add(CAMPUS_ID_FIELD);
        toDeletes.add(CAMPUS_GROUP_ID_FIELD);
        toDeletes.add(BUILDING_ID_FIELD);
        toDeletes.add(DORMITORY_ID_FIELD);
        redisTemplate.delete(toDeletes);

    }
}
