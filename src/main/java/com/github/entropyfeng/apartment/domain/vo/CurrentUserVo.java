package com.github.entropyfeng.apartment.domain.vo;

import org.springframework.util.StringUtils;

public class CurrentUserVo {

    String avatar;
    String username;
    String nickName;
    String trueName;
    String chinaIdNumber;
    String email;
    String phone;
    String title;
    String userid;
    String access;
    String signature;
    String group;


    public CurrentUserVo(String avatar, String username, String userid) {
        if (StringUtils.isEmpty(avatar)){
            this.avatar="https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png";
        }else {
            this.avatar = avatar;
        }

        this.username = username;
        this.userid = userid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getChinaIdNumber() {
        return chinaIdNumber;
    }

    public void setChinaIdNumber(String chinaIdNumber) {
        this.chinaIdNumber = chinaIdNumber;
    }
}
