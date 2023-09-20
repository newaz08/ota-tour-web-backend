package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum TravelMode {
    WITH_FLIGHT("With Flight"), WITHOUT_FLIGHT("Without Flight");

    private final String value;

    TravelMode(String value) {
        this.value = value;
    }
}
