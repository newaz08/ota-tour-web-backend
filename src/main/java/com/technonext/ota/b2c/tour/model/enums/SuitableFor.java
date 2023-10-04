package com.technonext.ota.b2c.tour.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuitableFor {

    FRIENDS("Friends"), FAMILY("Family"), FRIENDS_AND_FAMILY("Friends & Family");

    private final String value;
}
