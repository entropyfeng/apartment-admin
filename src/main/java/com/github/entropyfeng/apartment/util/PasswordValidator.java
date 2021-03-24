package com.github.entropyfeng.apartment.util;


import com.github.entropyfeng.apartment.exception.PasswordLengthException;
import com.github.entropyfeng.apartment.exception.PasswordStrengthException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidator {


    public void validatePassword(@NonNull String password)throws PasswordStrengthException,PasswordLengthException{

       validatePasswordLength(password);
       validatePasswordStrength(password);
    }

    public boolean preCheckPassword(@NonNull String password){
        return !password.isEmpty();
    }
    public  void validatePasswordLength(String password)throws PasswordLengthException {

        if (password.isEmpty()){
            throw new PasswordLengthException();
        }
    }
    public void validatePasswordStrength(String password)throws PasswordStrengthException {

        if (password.isEmpty()){
            throw new PasswordStrengthException();
        }

    }
}
