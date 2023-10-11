package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryStatus {
    NEW(0),
    LEAD_GENERATED(1),
    BOOKED(2),
    DECLINED(3),
    CLOSED(4);
    private final Integer value;

    InquiryStatus(Integer value) {
        this.value = value;
    }
}
