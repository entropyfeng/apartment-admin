package com.github.entropyfeng.apartment.config.bean;

import com.github.entropyfeng.apartment.domain.to.ApartmentScheduleCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ApartmentScheduleMap {



    private final ConcurrentHashMap<String,String> studentScheduleMap=new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, ApartmentScheduleCache> cacheMap=new ConcurrentHashMap<>();


    public ConcurrentHashMap<String, String> getStudentScheduleMap() {
        return studentScheduleMap;
    }

    public ConcurrentHashMap<String, ApartmentScheduleCache> getCacheMap() {
        return cacheMap;
    }

}
