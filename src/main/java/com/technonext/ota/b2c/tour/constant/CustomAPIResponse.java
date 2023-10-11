package com.technonext.ota.b2c.tour.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
 * We will not this response generally.
 * If needed, this class needs to be customized.
 * */

@AllArgsConstructor
@Getter
public class CustomAPIResponse {
    private final HttpStatus status;
    private final String message;

    public CustomAPIResponse(String message) {
        this.status = HttpStatus.OK;
        this.message = message;
    }
}