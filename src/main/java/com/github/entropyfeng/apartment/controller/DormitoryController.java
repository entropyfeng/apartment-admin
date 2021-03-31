package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.domain.vo.SimpleDormitoryVO;
import com.github.entropyfeng.apartment.service.DormitoryService;
import com.github.entropyfeng.common.domain.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DormitoryController {

    private final DormitoryService dormitoryService;

    public DormitoryController(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    @GetMapping("/apartment/dormitory")
    public Message acquireDormitoryByBuildingId(@Param("buildingId")Integer buildingId){

        Message message=new Message();
        List<SimpleDormitoryVO> dormitoryVOList= dormitoryService.queryDormitories(buildingId);
        message.setSuccess(true);
        message.addData("dormitories",dormitoryVOList);
        return message;
    }
}
