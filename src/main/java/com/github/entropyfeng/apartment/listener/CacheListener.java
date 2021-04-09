package com.github.entropyfeng.apartment.listener;

import com.github.entropyfeng.apartment.config.cache.CampusCache;
import com.github.entropyfeng.apartment.config.cache.CollegeCache;
import com.github.entropyfeng.apartment.dao.BuildingDao;
import com.github.entropyfeng.apartment.dao.CollegeDao;
import com.github.entropyfeng.apartment.domain.po.College;
import com.github.entropyfeng.apartment.domain.to.BuildingAndGroup;
import com.github.entropyfeng.apartment.event.CacheEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CacheListener implements ApplicationListener<CacheEvent> {

    final
    CollegeDao collegeDao;

    final
    CollegeCache collegeCache;

    final BuildingDao buildingDao;
    final CampusCache campusCache;
    private static final Logger logger= LoggerFactory.getLogger(CacheListener.class);
    @Autowired
    public CacheListener(CollegeDao collegeDao, CollegeCache collegeCache, BuildingDao buildingDao, CampusCache campusCache) {
        this.collegeDao = collegeDao;
        this.collegeCache = collegeCache;
        this.campusCache=campusCache;
        this.buildingDao=buildingDao;
    }

    @Override
    public void onApplicationEvent(CacheEvent cacheEvent) {

        logger.info("init cache");
        List<College> colleges = collegeDao.queryAllCollege();
        colleges.forEach(college -> collegeCache.addEntry(college.getCollegeId(), college.getCollegeName()));

        List<BuildingAndGroup> buildingAndGroups = buildingDao.queryRelativeMap();
        buildingAndGroups.forEach(campusCache::addEntry);
    }
}
