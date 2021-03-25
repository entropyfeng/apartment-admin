package com.github.entropyfeng.apartment.config.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CollegeCache {

    private final ConcurrentHashMap<Integer, String> collegeIdNameMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> collegeNameIdMap = new ConcurrentHashMap<>();

    public void addEntry(Integer collegeId, String collegeName) {
        collegeIdNameMap.put(collegeId, collegeName);
        collegeNameIdMap.put(collegeName, collegeId);
    }

    public void clear() {
        collegeNameIdMap.clear();
        collegeIdNameMap.clear();
    }

    public String getName(Integer id) {
        return collegeIdNameMap.get(id);
    }

    public Integer getId(String name) {
        return collegeNameIdMap.get(name);
    }
}
