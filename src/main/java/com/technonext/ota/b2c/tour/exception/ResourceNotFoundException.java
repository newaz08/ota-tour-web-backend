package com.technonext.ota.b2c.tour.exception;


import com.technonext.ota.b2c.tour.constant.ExceptionConstant;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }

}
