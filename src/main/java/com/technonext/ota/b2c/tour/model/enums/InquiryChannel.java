package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum InquiryChannel {
    WEB("Web"), MOBILE("Mobile");
    private final String value;

    InquiryChannel(String value) {
        this.value = value;
    }
}
