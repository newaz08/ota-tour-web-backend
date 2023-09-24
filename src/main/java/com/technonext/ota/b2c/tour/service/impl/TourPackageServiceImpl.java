package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.repository.TourPackageRepository;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageServiceImpl implements TourPackageService {

    @Autowired
    private TourPackageRepository tourPackageRepository;
    @Override
    public PackagePriceLimitResponse getPackagePriceLimit(Integer locationId) {
        return tourPackageRepository.getPackagePriceLimitByLocation(locationId);
    }
}
