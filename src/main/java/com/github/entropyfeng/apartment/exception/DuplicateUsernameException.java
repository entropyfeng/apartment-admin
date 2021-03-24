package com.github.entropyfeng.apartment.exception;

/**
 * @author entropyfeng
 */
public class DuplicateUsernameException extends RuntimeException {

    private String currentUsername;
    public DuplicateUsernameException() {
    }

    public DuplicateUsernameException(String currentUsername){
        this.currentUsername=currentUsername;
    }



    public DuplicateUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUsernameException(Throwable cause) {
        super(cause);
    }

    public DuplicateUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }
}
