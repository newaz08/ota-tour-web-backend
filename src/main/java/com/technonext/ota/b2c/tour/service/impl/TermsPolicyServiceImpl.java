package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.repository.ChildPolicyRepository;
import com.technonext.ota.b2c.tour.repository.RefundPolicyRepository;
import com.technonext.ota.b2c.tour.repository.TourPackageRepository;
import com.technonext.ota.b2c.tour.service.iservice.TermsPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsPolicyServiceImpl implements TermsPolicyService {

    private final ChildPolicyRepository childPolicyRepository;
    private final RefundPolicyRepository refundPolicyRepository;
    private final TourPackageRepository tourPackageRepository;

    @Override
    public List<String> getChildPolicies(Integer tourPackageId) {
        return childPolicyRepository.findChildPolicyDescription(tourPackageId);
    }

    @Override
    public List<String> getRefundPolicies(Integer tourPackageId) {
        return refundPolicyRepository.findRefundPolicyDescription(tourPackageId);
    }

    @Override
    public String getOtherPolicy(Integer tourPackageId) {
        return tourPackageRepository.getPackageOtherPolicy(tourPackageId);
    }
}
