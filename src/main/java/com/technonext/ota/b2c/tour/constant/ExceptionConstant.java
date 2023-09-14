package com.technonext.ota.b2c.tour.constant;

public enum ExceptionConstant {
    ACTION_NOT_FOUND("Action not found"),
    MODULE_NOT_FOUND("Module not found by Id");

    private final String message;

    ExceptionConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
