package com.github.entropyfeng.apartment.controller;



import com.github.entropyfeng.apartment.domain.po.AuthUser;
import com.github.entropyfeng.apartment.domain.to.LoginTo;
import com.github.entropyfeng.apartment.domain.vo.CurrentUserVo;
import com.github.entropyfeng.apartment.exception.AuthUserNotExistException;
import com.github.entropyfeng.apartment.exception.PasswordErrorException;
import com.github.entropyfeng.apartment.service.AuthUserService;
import com.github.entropyfeng.common.config.anno.CurrentUserAnno;
import com.github.entropyfeng.common.domain.CurrentUser;
import com.github.entropyfeng.common.domain.Message;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
public class AccountController {

    private final AuthUserService authUserService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }


    @GetMapping("/account/currentUser")
    public Message acquireCurrentUser(@ApiIgnore @CurrentUserAnno CurrentUser currentUser) {

        Message message = new Message();
        Long authUserId = currentUser.getUserId();
        try {
            AuthUser authUser = authUserService.getAuthUserById(authUserId);
            CurrentUserVo currentUserVo = new CurrentUserVo(authUser.getAvatar(), authUser.getAuthUsername(), authUser.getAuthUserId().toString());
            currentUserVo.setAccess("admin");
            message.addData("current_user", currentUserVo);

            message.setSuccess(true);
        } catch (AuthUserNotExistException e) {
            logger.info("not exist {}", authUserId);
        }

        return message;
    }

    @PutMapping("/account/currentUser")
    public Message updateCurrentUser(@CurrentUserAnno CurrentUser currentUser, @RequestBody CurrentUserVo userVo) {
        Message message = new Message();
        Long authUserId = currentUser.getUserId();
        logger.info(userVo.toString());

        return Message.ok();
    }

    @PostMapping("/account/login")
    public Message accountLoginByJson(@RequestBody LoginTo loginTo) {


        String username = loginTo.getUsername();
        String password = loginTo.getPassword();

        Message message = new Message();
        try {
            String token = authUserService.userLogin(username, password);
            Long authUserId = authUserService.getAuthUserIdByName(username);
            message.setSuccess(true);
            message.addData("token", token);
            message.addData("auth_user_id", authUserId);
            message.addData("loginType","account");
            return message;
        } catch (PasswordErrorException e) {

            logger.info("账户密码不匹配 {}", username);
        } catch (AuthUserNotExistException e) {
            logger.info("不存在该账户 {}", username);
        }
        message.setSuccess(false);
        message.setMsg("账户密码不匹配");
        return message;
    }

    @GetMapping("/account/captcha/email")
    public Message requireEmailCaptcha(@RequestParam("email") String email) {

        return Message.ok();
    }

    @GetMapping("/account/captcha/phone")
    public Message requirePhoneCaptcha(@RequestParam("phone") String phone) {
        return Message.ok();
    }

    @PostMapping("/account/login/phone")
    public Message phoneLogin(@RequestParam("phone") String phone, @RequestParam("code") String code) {

        return new Message();
    }

    @PostMapping("/account/login/email")
    public Message emailLogin(@RequestParam("email") String email, @RequestParam("code") String code) {

        return new Message();
    }

    @PostMapping("/account/loginOut")
    public Message loginOut(@CurrentUserAnno CurrentUser currentUser) {
        return Message.ok();
    }

}
