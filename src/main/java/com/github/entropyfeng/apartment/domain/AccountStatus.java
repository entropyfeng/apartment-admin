package com.github.entropyfeng.apartment.domain;

public enum AccountStatus {

    UNKNOWN(0,"未知"),COMMON(1,"正常"),LOCKED(2,"锁定"),EXPIRED(3,"过期");
    private final String msg;
    private final Integer code;
    AccountStatus(Integer code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
    public static AccountStatus toAccountStatus(String target){

        switch (target){
            case  "COMMON":return COMMON;
            case "LOCKED":return LOCKED;
            case "EXPIRED":return EXPIRED;
            default:return UNKNOWN;
        }
    }
    public static AccountStatus getAccountStatusByCode(Integer code) {
        switch (code) {
            case 1:
                return COMMON;
            case 2:
                return LOCKED;
            case 3:
                return EXPIRED;
            default:
                return UNKNOWN;
        }
    }

}
