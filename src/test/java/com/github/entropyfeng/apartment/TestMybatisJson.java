package com.github.entropyfeng.apartment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.entropyfeng.apartment.dao.ApartmentScheduleDao;
import com.github.entropyfeng.apartment.dao.DormitoryDao;
import com.github.entropyfeng.apartment.domain.po.*;
import com.github.entropyfeng.apartment.service.ApartmentScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@SpringBootTest
public class TestMybatisJson {

    @Autowired
    ApartmentScheduleDao scheduleDao;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    DormitoryDao dormitoryDao;
    @Autowired
    ApartmentScheduleService scheduleService;
    @Test
    public void test1() throws JsonProcessingException {


        redisTemplate.opsForValue().set("beginTime1","我认为让他");
        System.out.println(redisTemplate.opsForValue().get("beginTime1"));

        redisTemplate.opsForValue().getOperations().expireAt("beginTime1",new Date());
        System.out.println(redisTemplate.opsForValue().get("beginTime1"));
        List<String> strings=new ArrayList<>();
        strings.add("10087");
        strings.add("qqeeqeq");
        scheduleDao.truncateApartmentSchedule();
        scheduleDao.insertApartmentSchedule("2021搬迁",strings);
        System.out.println("xx");

        List<String> toExt=new ArrayList<>();
        toExt.add("789");
        toExt.add("666");
        toExt.add("10087");
        scheduleDao.extendScheduleStudentId("2021搬迁",toExt);
        ApartmentSchedule xxx=scheduleDao.queryTargetStudentId("2021搬迁");
        System.out.println("qq");
        CampusGroupBlock groupBlock=new CampusGroupBlock();
        groupBlock.setCampusGroupId(10);
        groupBlock.setCampusGroupName("桃子");
        List<BuildingBlock> buildingBlocks=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BuildingBlock buildingBlock=new BuildingBlock();
            buildingBlock.setBuildingId(i);
            buildingBlock.setBuildingName(i+"号楼");
            buildingBlocks.add(buildingBlock);
        }
        List<CampusGroupBlock> campusGroupBlocks=new ArrayList<>();
        campusGroupBlocks.add(groupBlock);
        groupBlock.setBuildingBlocks(buildingBlocks);

        scheduleDao.insertTargetApartment("2022",campusGroupBlocks);

        ApartmentSchedule schedule=scheduleDao.queryTargetApartment("2022");
        System.out.println("xx");
/*
     String json="[\"10087\", \"qqeeqeq\"]";
        ObjectMapper objectMapper=new ObjectMapper();
        List<String> xxxx=objectMapper.readValue(json,List.class);

        System.out.println(xxxx);*/
    }





    @Test
    public void test3() throws JsonProcessingException {

        //dormitoryDao.updateTargetResident(1,0,"qw147");
       //scheduleDao.initApartmentSchedule("2022男搬迁","2022",new Date(),new Date());
        List<String> toExt=new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            toExt.add("789"+i);
            toExt.add(i+"666");
            toExt.add(i+"10087");
        }

        List<Integer> bedIds=new ArrayList<>();
        bedIds.add(1);
        bedIds.add(2);
        scheduleService.addDorIntoSchedule("2022男搬迁",200000000,bedIds);
        //scheduleDao.extendScheduleStudentId("2021搬迁",toExt);
        //dormitoryDao.updateTargetResident();
    }

    @Test
    public void testCheck(){
       Integer res1= dormitoryDao.checkInTargetResident(2,2,"qwwqewqeqe");
       Integer res2= dormitoryDao.checkOutTargetResident(2,2);
        System.out.println(res1+"--"+res2);
    }
}
