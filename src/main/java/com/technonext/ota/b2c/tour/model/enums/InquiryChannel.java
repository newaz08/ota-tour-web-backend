package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryChannel {
    WEB(0), MOBILE(1);
    private final Integer value;

    InquiryChannel(Integer value) {
        this.value = value;
    }
}
