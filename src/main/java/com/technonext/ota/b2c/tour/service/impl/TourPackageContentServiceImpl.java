package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageContentResponse;
import com.technonext.ota.b2c.tour.repository.TourPackageContentRepository;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TourPackageContentServiceImpl implements TourPackageContentService {

    @Value("${application.base-url}")
    private String baseUrl;
    private final TourPackageContentRepository tourPackageContentRepository;


    public TourPackageContentServiceImpl(TourPackageContentRepository tourPackageContentRepository) {
        this.tourPackageContentRepository = tourPackageContentRepository;
    }

    @Override
    public List<TourPackageContentResponse> getAllTourPackageContentByPackageId(Integer tourPackageId) {
        return tourPackageContentRepository.findTourPackageContent(tourPackageId,baseUrl);
    }

    @Override
    public PackagePriceLimitResponse getPackagePriceLimit(Integer locationId) {
        return tourPackageContentRepository.getPackagePriceLimitByLocation(locationId);
    }
}
