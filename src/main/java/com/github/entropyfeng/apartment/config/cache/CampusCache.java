package com.github.entropyfeng.apartment.config.cache;

import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class CampusCache {

   private final Map<Integer, BuildingAndGroup> map = new ConcurrentHashMap<>();

   public void addEntry(Integer buildingId,BuildingAndGroup buildingAndGroup){
       map.put(buildingId, buildingAndGroup);
   }
    public BuildingAndGroup getBuildingAndGroup(Integer buildingId) {
        return map.get(buildingId);
    }
    public Map<Integer, BuildingAndGroup> getMap(){
       return map;
    }
}
