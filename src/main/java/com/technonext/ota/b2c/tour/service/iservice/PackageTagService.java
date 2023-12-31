package com.technonext.ota.b2c.tour.service.iservice;


import com.technonext.ota.b2c.tour.dto.response.NextTripTagWithPackageInfoResponse;
import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageTagProjection;

import java.util.List;

public interface PackageTagService {
    List<PackageTagResponse> getPackageTagsByLocation(Integer locationId);
    NextTripTagWithPackageInfoResponse getNextTripTagWithPackageInfoGroupByCountry();

    List<PackageTagResponse> getTopFiveNextTripTag();

    List<TourPackageTagProjection>getPackageTagsByTourPackageId(Integer tourPackageId);
}
