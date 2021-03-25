package com.github.entropyfeng.apartment.domain;

public enum  StudentStatus {

    UNKNOWN(0,"未知"),
    DEATH(1,"死亡"),
    SUSPENSION(2,"休学"),
    REGISTERED(3,"当学期已注册"),
    NOT_REGISTERED(4,"当学期未注册"),
    ADMITTED_NOT_REGISTERED(5,"已录取未注册"),
    GRADUATION(6,"毕业"),
    COMPLETION(7,"结业"),
    IN_COMPLETION(8,"肄业");

    private final String msg;
    private final Integer code;
    StudentStatus(Integer code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
    public static StudentStatus toStudentStatus(String target){

        switch (target){
            case  "DEATH":return DEATH;
            case "SUSPENSION":return SUSPENSION;
            case "REGISTERED":return REGISTERED;
            case "NOT_REGISTERED":return NOT_REGISTERED;
            case "ADMITTED_NOT_REGISTERED":return ADMITTED_NOT_REGISTERED;
            case "GRADUATION":return GRADUATION;
            case "COMPLETION":return COMPLETION;
            case "IN_COMPLETION":return IN_COMPLETION;
            default:return UNKNOWN;
        }
    }
    public static StudentStatus getStudentStatusByCode(Integer code) {
        switch (code) {
            case 1:
                return DEATH;
            case 2:
                return SUSPENSION;
            case 3:
                return REGISTERED;
            case 4:
                return NOT_REGISTERED;
            case 5:
                return ADMITTED_NOT_REGISTERED;
            case 6:
                return GRADUATION;
            case 7:
                return COMPLETION;
            case 8:
                return IN_COMPLETION;
            default:
                return UNKNOWN;
        }
    }

}
