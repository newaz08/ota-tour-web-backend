package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.TermsPolicyResponse;

import java.util.List;

public interface TermsPolicyService {
    TermsPolicyResponse getChildAndRefundPolicyDescription();
    List<String> getChildPolicies(Integer tourPackageId);
    List<String> getRefundPolicies(Integer tourPackageId);
    String getOtherPolicy(Integer tourPackageId);

}
