package com.knd.network.errorhandler;

public class CustomeServerException extends Exception {
    private   String code;
    public CustomeServerException(String message,String code) {
        super(message);
        this.code=code;
    }

    public String getCode() {
        return code;
    }
}
