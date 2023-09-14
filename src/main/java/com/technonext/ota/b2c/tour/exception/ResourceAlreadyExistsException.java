package com.technonext.ota.b2c.tour.exception;

import com.technonext.ota.b2c.tour.constant.ExceptionConstant;

public class ResourceAlreadyExistsException extends ApplicationException {
    public ResourceAlreadyExistsException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }
}
