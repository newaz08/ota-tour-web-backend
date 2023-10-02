package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageContentResponse;

import java.util.List;

public interface TourPackageContentService {
    List<TourPackageContentResponse> getAllTourPackageContentByPackageId(Integer tourPackageId);
    PackagePriceLimitResponse getPackagePriceLimit(Integer locationId);
}
