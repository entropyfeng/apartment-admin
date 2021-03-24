package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.vo.SimpleDormitoryVO;
import com.github.entropyfeng.apartment.service.OrderDormitoryService;
import com.github.entropyfeng.common.config.anno.CurrentUserAnno;
import com.github.entropyfeng.common.domain.CurrentUser;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDormitoryController {


    final
    OrderDormitoryService orderService;

    @Autowired
    public OrderDormitoryController(OrderDormitoryService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/apartment/university/available/dormitories")
    public Message acquireAvailableDormitory(@RequestParam("buildingName")String buildingName,@CurrentUserAnno CurrentUser currentUser){
        Message message=new Message();
        message.setSuccess(true);
        String username=currentUser.getUserName();
        List<SimpleDormitoryVO> simpleDormitoryVOS= orderService.filterAvailableDormitory(username,buildingName);
        message.addData("dormitories",simpleDormitoryVOS);
        return message;
    }

    @GetMapping("/apartment/university/available/campus/names")
    public Message acquireAvailableCampusName(@CurrentUserAnno CurrentUser currentUser){
      List<String> names=  orderService.filterAvailableCampusName(currentUser.getUserName());

      Message message=new Message();
      message.setSuccess(true);
      message.addData("names",names);
      return message;
    }

    @GetMapping("/apartment/university/available/campusGroup/names")
    public Message acquireAvailableCampusGroupName(@RequestParam("campusName")String campusName,@CurrentUserAnno CurrentUser currentUser){
        List<String> names=orderService.filterAvailableCampusGroup(currentUser.getUserName(),campusName);
        Message message=new Message();
        message.setSuccess(true);
        message.addData("names",names);
        return message;
    }
    @GetMapping("/apartment/university/available/building/names")
    public Message acquireAvailableBuildingsName(@RequestParam("campusGroupName") String campusGroupName, @CurrentUserAnno CurrentUser currentUser){

        List<String> names=orderService.filterAvailableBuildingName(currentUser.getUserName(),campusGroupName);
        Message message=new Message();
        message.setSuccess(true);
        message.addData("names",names);
        return message;
    }
    @GetMapping("/apartment/university/available/global/names")
    public Message acquireAvailableGlobalName(){



        return Message.ok();
    }

}
