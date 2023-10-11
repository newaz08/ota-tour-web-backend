package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryType {
    GENERAL(0), TOUR_SPECIFIC(1);
    private final Integer value;

    InquiryType(Integer value) {
        this.value = value;
    }
}
