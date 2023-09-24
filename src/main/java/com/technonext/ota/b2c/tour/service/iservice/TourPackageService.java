package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;

public interface TourPackageService {
    PackagePriceLimitResponse getPackagePriceLimit(Integer locationId);
}
