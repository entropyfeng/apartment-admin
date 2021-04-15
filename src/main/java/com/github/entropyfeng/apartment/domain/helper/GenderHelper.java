package com.github.entropyfeng.apartment.domain.helper;

import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.InGender;

public class GenderHelper {

    public static Gender toGender(InGender inGender){
        switch (inGender){
            case WOMAN:return Gender.WOMAN;
            case MAN:return Gender.MAN;
            case UNKNOWN:return Gender.UNKNOWN;
            default:throw new RuntimeException("illegal gender convert");
        }
    }
    public static InGender toInGender(Gender gender){
        switch (gender){

            case MAN:return InGender.MAN;
            case WOMAN:return InGender.WOMAN;
            default:return InGender.UNKNOWN;
        }
    }

}
