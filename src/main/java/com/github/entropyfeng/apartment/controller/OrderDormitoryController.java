package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.vo.DormitoryVO;
import com.github.entropyfeng.apartment.service.OrderDormitoryService;
import com.github.entropyfeng.common.config.anno.CurrentUserAnno;
import com.github.entropyfeng.common.domain.CurrentUser;
import com.github.entropyfeng.common.domain.Message;
import com.github.entropyfeng.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
    public Message acquireAvailableDormitory(@RequestParam("buildingName") String buildingName, @ApiIgnore @CurrentUserAnno CurrentUser currentUser) {
        Message message = new Message();
        message.setSuccess(true);
        String username = currentUser.getUserName();
        List<DormitoryVO> simpleDormitoryVOS = orderService.filterAvailableDormitory(currentUser.getRoles(),username, buildingName);
        message.addData("dormitories", simpleDormitoryVOS);
        return message;
    }

    @GetMapping("/apartment/university/available/campus/names")
    public Message acquireAvailableCampusNames(@ApiIgnore @CurrentUserAnno CurrentUser currentUser) {

        List<String> names = orderService.filterAvailableCampusName(currentUser.getRoles(),currentUser.getUserName());
        Message message = new Message();
        message.setSuccess(true);
        message.addData("isLeaf", false);
        message.addData("names", names);
        return message;
    }

    @GetMapping("/apartment/university/available/campusGroup/names")
    public Message acquireAvailableCampusGroupNames(@RequestParam("campusName") String campusName, @ApiIgnore @CurrentUserAnno CurrentUser currentUser) {
        List<String> names = orderService.filterAvailableCampusGroupName(currentUser.getRoles(),currentUser.getUserName(), campusName);
        Message message = new Message();
        message.setSuccess(true);
        message.addData("names", names);
        message.addData("isLeaf", false);
        return message;
    }

    @GetMapping("/apartment/university/available/building/names")
    public Message acquireAvailableBuildingNames(@RequestParam("campusGroupName") String campusGroupName, @ApiIgnore @CurrentUserAnno CurrentUser currentUser) {

        List<String> names = orderService.filterAvailableBuildingName(currentUser.getRoles(),currentUser.getUserName(), campusGroupName);
        Message message = new Message();
        message.setSuccess(true);
        message.addData("names", names);
        message.addData("isLeaf", true);
        return message;
    }

    @GetMapping("/apartment/my/status")
    public Message acquireMyDormitoryStatus(@ApiIgnore @CurrentUserAnno CurrentUser currentUser){

       Boolean exist= orderService.hasInDormitory(currentUser.getUserName());
        Message message=new Message();
        message.setSuccess(true);
        message.addData("myStatus",exist);
        return message;

    }

    @PostMapping("/apartment/my/checkIn")
    public Message checkInMyDormitory(@ApiIgnore @CurrentUserAnno CurrentUser currentUser, @RequestParam("dormitoryId") Integer dormitoryId, @RequestParam("bedId") Integer bedId) {
        Message message = new Message();

        message.setSuccess(true);
        orderService.checkInDormitory(currentUser.getUserName(), dormitoryId, bedId);
        message.addData("checkInStatus", true);

        return message;
    }

    @PostMapping("/apartment/my/checkOut")
    public Message checkOutMyDormitory(@ApiIgnore @CurrentUserAnno CurrentUser currentUser) {

        Message message = new Message();
        message.setSuccess(true);
        try {
            orderService.checkOutDormitory(currentUser.getUserName());
            message.addData("checkOutStatus", true);
        } catch (BusinessException e) {
            message.addData("checkOutStatus", false);
            e.printStackTrace();
        }


        return message;
    }
}
