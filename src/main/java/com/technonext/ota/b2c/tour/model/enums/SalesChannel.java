package com.technonext.ota.b2c.tour.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SalesChannel {

    FRONT_OFFICE("Front Office", 1),
    BACK_OFFICE("Back Office", 2),
    BOTH("Both", 3),
    B2B("B2B", 4),
    MOBILE("Mobile", 5),
    ALL("All", 6);

    private final String value;
    private final Integer intValue;
}
