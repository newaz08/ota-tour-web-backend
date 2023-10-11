package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryFor {
    TOUR_PACKAGES("Tour Packages"), UMRAH_PACKAGES("Hajj-Umrah Packages");
    private final String value;

    InquiryFor(String value) {
        this.value = value;
    }
}
