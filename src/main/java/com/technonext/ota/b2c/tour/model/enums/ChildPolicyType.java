package com.technonext.ota.b2c.tour.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChildPolicyType {

    NO_FEE("No Fee"), PARTIAL_FEE("Partial Fee");

    private final String value;
}
