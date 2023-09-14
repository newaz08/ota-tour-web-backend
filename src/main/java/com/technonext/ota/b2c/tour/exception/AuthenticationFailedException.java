package com.technonext.ota.b2c.tour.exception;

import com.technonext.ota.b2c.tour.constant.ExceptionConstant;
public class AuthenticationFailedException extends ApplicationException {

    public AuthenticationFailedException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }
}
