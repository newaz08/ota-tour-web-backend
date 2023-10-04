package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryFor {
    Tour_Packages("Tour Packages"), Umrah_Packages("Hajj-Umrah Packages");
    private final String value;

    InquiryFor(String value) {
        this.value = value;
    }
}
