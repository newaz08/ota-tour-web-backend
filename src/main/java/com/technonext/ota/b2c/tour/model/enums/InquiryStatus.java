package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryStatus {
    NEW(1),
    LEAD_GENERATED(2),
    BOOKED(3),
    DECLINED(4),
    CLOSED(5);
    private final Integer value;

    InquiryStatus(Integer value) {
        this.value = value;
    }
}
