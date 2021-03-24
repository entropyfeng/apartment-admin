package com.github.entropyfeng.apartment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author entropyfeng
 * @date 2019/6/12 18:16
 */
@Component
@ConfigurationProperties(prefix = "auth.config")
public class AuthProperties {

    public String jwtSecretKey = "?::43dsd43fdf4fdf48487456cvf):";
    public String salt="da_()j32sldjlkfaxzcxc";
    public String issuer = "default";
    public Long duration = 999999999999999L;
    public TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    public String captchaSuffix = "CAPTCHA_TOKEN_";
    public Long machineId = 1L;
    public Long dataCenterId = 1L;
    /**
     * the expired time of the captcha
     */
    public Integer captchaExpiredTime = 300;

    public Long getDataCenterId() {
        return dataCenterId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public String getJwtSecretKey() {
        return jwtSecretKey;
    }

    public void setJwtSecretKey(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    public String getCaptchaSuffix() {
        return captchaSuffix;
    }

    public void setCaptchaSuffix(String captchaSuffix) {
        this.captchaSuffix = captchaSuffix;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public void setDataCenterId(Long dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public Integer getCaptchaExpiredTime() {
        return captchaExpiredTime;
    }

    public void setCaptchaExpiredTime(Integer captchaExpiredTime) {
        this.captchaExpiredTime = captchaExpiredTime;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
