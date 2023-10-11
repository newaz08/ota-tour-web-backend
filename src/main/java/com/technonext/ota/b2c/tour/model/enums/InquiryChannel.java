package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryChannel {
    WEB(1), MOBILE(2);
    private final Integer value;

    InquiryChannel(Integer value) {
        this.value = value;
    }
}
