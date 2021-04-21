package com.github.entropyfeng.apartment.domain.po;

import java.util.Date;

public class AuthUserRole {

    private Long authRoleId;
    private Long authUserId;
    private Date createTime;
    private Date updateTime;

    public Long getAuthRoleId() {
        return authRoleId;
    }

    public void setAuthRoleId(Long authRoleId) {
        this.authRoleId = authRoleId;
    }


    public Long getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Long authUserId) {
        this.authUserId = authUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
