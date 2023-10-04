package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryStatus {
    NEW_INQUIRY("New Inquiry"), LEAD_GENERATED("Lead Generated"), BOOKED("Booked"), CLOSED("Closed");
    private final String value;

    InquiryStatus(String value) {
        this.value = value;
    }
}
