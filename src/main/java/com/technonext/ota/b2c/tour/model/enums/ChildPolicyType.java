package com.technonext.ota.b2c.tour.model.enums;

import lombok.Getter;

@Getter
public enum ChildPolicyType {
    NO_FEE("No Fee"), PARTIAL_FEE("Partial Fee");

    private final String value;

    ChildPolicyType(String value) {
        this.value = value;
    }
}
