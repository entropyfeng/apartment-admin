package com.github.entropyfeng.apartment.domain;

public enum InGender {
    UNKNOWN(0,"未知"), MAN(1,"男"),WOMAN(2,"女"),MIX(3,"混合");


    private final String msg;
    private final Integer code;
    InGender(Integer code,String msg) {
        this.code=code;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
    public static InGender toInGender(String target){

        switch (target){
            case  "WOMAN":return InGender.WOMAN;
            case "MAN":return InGender.MAN;
            case "MIX":return InGender.MIX;
            default:return InGender.UNKNOWN;
        }
    }
    public static InGender getInGenderByCode(Integer code) {
        switch (code) {
            case 1:
                return MAN;
            case 2:
                return WOMAN;
            case 3:
                return MIX;
            default:
                return UNKNOWN;
        }
    }

}
