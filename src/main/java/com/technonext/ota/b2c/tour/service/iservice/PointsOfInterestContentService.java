package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestContentProjection;
import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestProjection;

import java.util.List;

public interface PointsOfInterestContentService {

    List<PointsOfInterestContentProjection> getPointsOfInterestContentById(Long pointsOfInterestId);
    List<PointsOfInterestProjection> getPackagePointOfInterest(Integer locationId);
}
