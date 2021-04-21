package com.github.entropyfeng.apartment.controller;

import com.github.entropyfeng.apartment.domain.po.AuthResource;
import com.github.entropyfeng.apartment.service.AuthResourceService;
import com.github.entropyfeng.common.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthResourceController {

    @Autowired
    AuthResourceService authResourceService;

    @GetMapping("/auth/resource/all")
    public Message acquireAllAuthResource() {
        Message message = new Message();
        message.setSuccess(true);
        List<AuthResource> resources = authResourceService.allResource();
        message.addData("resources", resources);
        return message;
    }

}
