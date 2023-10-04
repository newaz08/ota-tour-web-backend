package com.technonext.ota.b2c.tour.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PackageMode {

    NIGHT_STAY("Night Stay"), ACTIVITY_BASED("Activity Based");

    private final String value;
}
