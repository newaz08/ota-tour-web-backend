package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryFor {
    TOUR_PACKAGES(0), UMRAH_PACKAGES(1);
    private final Integer value;

    InquiryFor(Integer value) {
        this.value = value;
    }
}
