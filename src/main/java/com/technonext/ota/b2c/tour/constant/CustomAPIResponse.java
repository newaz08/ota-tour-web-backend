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
public class CustomAPIResponse<T> {
    private final HttpStatus status;
    private final String message;
    private final T content;

    public CustomAPIResponse(T content) {
        this.status = HttpStatus.OK;
        this.message = "Successfully Done!";
        this.content = content;
    }
}