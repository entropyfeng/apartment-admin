package com.github.entropyfeng.apartment.domain;

public enum StudentAccountStatus {

    UNKNOWN(0,"未知"), EXIST(1,"存在"),NOT_EXIST(2,"不存在");

    private final String msg;
    private final Integer code;
    StudentAccountStatus(Integer code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
    public static StudentAccountStatus toStudentAccountStatus(String target){

        switch (target){
            case  "EXIST":return EXIST;
            case "NOT_EXIST":return NOT_EXIST;
            default:return UNKNOWN;
        }
    }
    public static StudentAccountStatus getStudentAccountStatusByCode(Integer code) {
        switch (code) {
            case 1:
                return EXIST;
            case 2:
                return NOT_EXIST;
            default:
                return UNKNOWN;
        }
    }

}
