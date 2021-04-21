package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.po.AuthRole;
import com.github.entropyfeng.apartment.service.AuthRoleService;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthRoleController {

    @Autowired
    AuthRoleService authRoleService;

    @GetMapping("/auth/role/all")
    public Message  acquireAllAuthRole(){
       Message message=new Message();
       message.setSuccess(true);
       List<AuthRole> roleList= authRoleService.allRoles();
        message.addData("roles",roleList);
        return message;
    }
}
