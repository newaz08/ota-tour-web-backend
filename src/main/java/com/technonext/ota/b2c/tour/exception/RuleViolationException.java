package com.technonext.ota.b2c.tour.exception;


import com.technonext.ota.b2c.tour.constant.ExceptionConstant;

public class RuleViolationException extends ApplicationException {

    public RuleViolationException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }

}
