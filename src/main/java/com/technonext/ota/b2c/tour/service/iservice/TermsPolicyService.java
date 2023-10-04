package com.technonext.ota.b2c.tour.service.iservice;


import java.util.List;

public interface TermsPolicyService {
    List<String> getChildPolicies(Integer tourPackageId);
    List<String> getRefundPolicies(Integer tourPackageId);
    String getOtherPolicy(Integer tourPackageId);

}
