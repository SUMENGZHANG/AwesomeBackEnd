package org.example.enums;

public enum ErrorCode {

    SUCCESS(200, "success"),

    USER_ALREADY_REGISTERED(204, "user already registered"),
    USER_NOT_FOUND(205, "user not found"),
    ;


    private int key;
    private String description;

    ErrorCode(int key, String description) {
        this.key = key;
        this.description = description;
    }
    public int getKey(){
        return key;
    }
    public String getDescription(){
        return description;
    }
}
