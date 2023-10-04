package com.technonext.ota.b2c.tour.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class TermsPolicyResponse {

    private String policyName;
    private List<String> policies;
}
