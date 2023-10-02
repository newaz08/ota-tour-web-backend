package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryType {
    GENERAL("General"), TOUR_SPECIFIC("Tour Specific");
    private final String value;

    InquiryType(String value) {
        this.value = value;
    }
}
