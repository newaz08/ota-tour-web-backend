package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestContentResponse;

import java.util.List;

public interface PointsOfInterestContentService {

    List<PointsOfInterestContentResponse> getPointsOfInterestContentById(Long pointsOfInterestId);
}
