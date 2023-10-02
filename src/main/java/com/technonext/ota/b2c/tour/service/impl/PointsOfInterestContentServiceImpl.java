package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestContentResponse;
import com.technonext.ota.b2c.tour.repository.PointsOfInterestContentRepository;
import com.technonext.ota.b2c.tour.service.iservice.PointsOfInterestContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointsOfInterestContentServiceImpl implements PointsOfInterestContentService{

    @Value("${application.base-url}")
    private String baseUrl;
    private final PointsOfInterestContentRepository pointsOfInterestContentRepository;

    public PointsOfInterestContentServiceImpl(PointsOfInterestContentRepository pointsOfInterestContentRepository) {
        this.pointsOfInterestContentRepository = pointsOfInterestContentRepository;
    }

    @Override
    public List<PointsOfInterestContentResponse> getPointsOfInterestContentById(Long pointsOfInterestId) {
        return pointsOfInterestContentRepository.findPointsOfInterestContentById(pointsOfInterestId,baseUrl);
    }
}

