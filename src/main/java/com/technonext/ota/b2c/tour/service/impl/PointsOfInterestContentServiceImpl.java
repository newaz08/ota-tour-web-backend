package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestContentProjection;
import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestProjection;
import com.technonext.ota.b2c.tour.repository.PointsOfInterestContentRepository;
import com.technonext.ota.b2c.tour.service.iservice.PointsOfInterestContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointsOfInterestContentServiceImpl implements PointsOfInterestContentService {

    @Value("${application.base-url}")
    private String baseUrl;
    private final PointsOfInterestContentRepository pointsOfInterestContentRepository;


    @Override
    public List<PointsOfInterestContentProjection> getPointsOfInterestContentById(Long pointsOfInterestId) {
        return pointsOfInterestContentRepository.findPointsOfInterestContentById(pointsOfInterestId, baseUrl);
    }

    @Override
    public List<PointsOfInterestProjection> getPackagePointOfInterest(Integer locationId) {
        return pointsOfInterestContentRepository.getPackagePointOfInterest(locationId, baseUrl);
    }
}

