package com.github.entropyfeng.apartment.domain;

public enum DormitoryDirection {
    UNKNOWN(0,"未知"),WEST(1,"朝西"),EAST(2,"朝东"),SOUTH(3,"朝南"),NORTH(4,"朝北");

    private final Integer code;

    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    DormitoryDirection(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static DormitoryDirection toDormitoryDirection(String target){

        if(target==null){
            return UNKNOWN;
        }
        switch (target){
            case  "WEST":return WEST;
            case "EAST":return EAST;
            case "SOUTH":return SOUTH;
            case "NORTH":return NORTH;
            default:return UNKNOWN;
        }
    }
    public static DormitoryDirection getInGenderByCode(Integer code) {
        switch (code) {
            case 1:
                return WEST;
            case 2:
                return EAST;
            case 3:
                return SOUTH;
            case 4:return NORTH;
            default:
                return UNKNOWN;
        }
    }
}
