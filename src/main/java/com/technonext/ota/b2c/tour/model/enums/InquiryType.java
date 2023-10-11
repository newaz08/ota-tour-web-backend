package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryType {
    GENERAL(1), TOUR_SPECIFIC(2);
    private final Integer value;

    InquiryType(Integer value) {
        this.value = value;
    }
}
