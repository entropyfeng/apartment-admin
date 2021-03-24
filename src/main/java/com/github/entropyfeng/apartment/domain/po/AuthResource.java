package com.github.entropyfeng.apartment.domain.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author entropyfeng
 */
public class AuthResource implements Serializable {

    private Long authResourceId;

    private String authResourceName;

    private String description;

    private String path;

    private String method;

    private String status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;


    public Long getAuthResourceId() {
        return authResourceId;
    }

    public void setAuthResourceId(Long authResourceId) {
        this.authResourceId = authResourceId;
    }

    public String getAuthResourceName() {
        return authResourceName;
    }

    public void setAuthResourceName(String authResourceName) {
        this.authResourceName = authResourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
