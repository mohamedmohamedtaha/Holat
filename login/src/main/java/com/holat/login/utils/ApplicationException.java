package com.holat.login.utils;

public class ApplicationException extends Throwable {
    private Type type;
    private final String message;

    public ApplicationException(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public enum Type {
        NO_INTERNET, NO_DATA, VALIDATION
    }
}