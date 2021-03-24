package com.github.entropyfeng.apartment.domain.vo;

import java.io.Serializable;

public class ConfigRolesVo implements Serializable {


    public ConfigRolesVo(String authRoleId, String authRoleName, String description, Boolean disabled) {
        this.authRoleId = authRoleId;
        this.authRoleName = authRoleName;
        this.description = description;
        this.disabled = disabled;
    }

    private String authRoleId;

    private String authRoleName;

    private String description;

    private Boolean disabled;

    public String getAuthRoleName() {
        return authRoleName;
    }

    public void setAuthRoleName(String authRoleName) {
        this.authRoleName = authRoleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getAuthRoleId() {
        return authRoleId;
    }

    public void setAuthRoleId(String authRoleId) {
        this.authRoleId = authRoleId;
    }
}
