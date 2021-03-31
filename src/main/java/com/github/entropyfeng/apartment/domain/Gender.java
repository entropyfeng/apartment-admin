package com.github.entropyfeng.apartment.domain;

public enum Gender {

    UNKNOWN(0,"未知"), MAN(1,"男"),WOMAN(2,"女");


    private final String msg;
    private final Integer code;
    Gender(Integer code,String msg) {
        this.code=code;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
    public static Gender toInGender(String target){

        switch (target){
            case  "WOMAN" :
            case  "女":return Gender.WOMAN;
            case "男":
            case "MAN":return Gender.MAN;
            default:return Gender.UNKNOWN;
        }
    }
    public static Gender getInGenderByCode(Integer code) {
        switch (code) {
            case 1:
                return MAN;
            case 2:
                return WOMAN;
            default:
                return UNKNOWN;
        }
    }

}
