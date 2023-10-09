package com.technonext.ota.b2c.tour.service.iservice;


import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageTagProjection;

import java.util.List;

public interface PackageTagService {
    List<PackageTagResponse> getPackageTagsByLocation(Integer locationId);

    List<TourPackageTagProjection>getPackageTagsByTourPackageId(Integer tourPackageId);
}
