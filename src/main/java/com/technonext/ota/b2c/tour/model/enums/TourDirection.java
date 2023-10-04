package com.technonext.ota.b2c.tour.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TourDirection {

    IN_BOUND("Inbound"), OUT_BOUND("Outbound");

    private final String value;
}
