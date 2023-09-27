package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageContentProjection;
import com.technonext.ota.b2c.tour.repository.TourPackageContentRepository;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageContentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TourPackageContentServiceImpl implements TourPackageContentService {
    private final TourPackageContentRepository tourPackageContentRepository;


    public TourPackageContentServiceImpl(TourPackageContentRepository tourPackageContentRepository) {
        this.tourPackageContentRepository = tourPackageContentRepository;
    }

    @Override
    public List<TourPackageContentProjection> getAllTourPackageContentByPackageId(Integer tourPackageId) {
        return tourPackageContentRepository.findTourPackageContent(tourPackageId);
    }

    @Override
    public PackagePriceLimitResponse getPackagePriceLimit(Integer locationId) {
        return tourPackageContentRepository.getPackagePriceLimitByLocation(locationId);
    }
}
