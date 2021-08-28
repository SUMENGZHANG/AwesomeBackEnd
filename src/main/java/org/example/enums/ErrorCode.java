package org.example.enums;

public enum ErrorCode {

    QUERY_SUCCESS(201, "query success"),
    INSERT_SUCCESS(202, "insert success"),
    UPDATE_SUCCESS(203, "update success"),
    DELETE_SUCCESS(204, "delete success"),

    USER_ALREADY_REGISTERED(205, "user already registered"),
    USER_NOT_FOUND(206, "user not found"),
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
