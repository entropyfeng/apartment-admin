package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.po.AuthUser;
import com.github.entropyfeng.apartment.domain.to.PageRequest;
import com.github.entropyfeng.apartment.service.AuthUserService;
import com.github.entropyfeng.common.domain.Message;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @GetMapping("/auth/user/all")
    public Message acquireAllUser(@Nullable @RequestParam("currentPage")Integer pageNo, @Nullable@RequestParam("pageSize")Integer pageSize) {
        Message message=new Message();
        List<AuthUser> userList;
        if (pageNo==null||pageSize==null){
            userList= authUserService.allAuthUser();
        }else {
            PageInfo<AuthUser> pageInfo= authUserService.allAuthUserByPages(new PageRequest(pageNo,pageSize));
            userList=pageInfo.getList();
            message.addData("total",pageInfo.getTotal());
            message.addData("current",pageInfo.getPageNum());
            message.addData("pageSize",pageInfo.getPageSize());
        }

        message.setSuccess(true);
        message.addData("auth_users",userList);
        return message;
    }
}
