package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.PackageDescriptionResponse;
import com.technonext.ota.b2c.tour.repository.TourPackageRepository;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageService;
import org.springframework.stereotype.Service;

@Service
public class TourPackageServiceImpl implements TourPackageService {

    private final TourPackageRepository tourPackageRepository;

    public TourPackageServiceImpl(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    @Override
    public PackageDescriptionResponse getTourPackageDescription(Integer tourPackageId) {
        return tourPackageRepository.getTourPackageDetailsById(tourPackageId);
    }
}
