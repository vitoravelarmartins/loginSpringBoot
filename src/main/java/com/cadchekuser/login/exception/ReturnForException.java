package com.cadchekuser.login.exception;


import lombok.Getter;

@Getter
public class ReturnForException {

    private String field;
    private String error;

    public ReturnForException(String field, String error) {
        this.field = field;
        this.error = error;
    }
}
