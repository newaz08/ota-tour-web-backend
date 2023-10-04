package com.technonext.ota.b2c.tour.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TravelMode {

    WITH_FLIGHT("With Flight"), WITHOUT_FLIGHT("Without Flight");

    private final String value;
}
