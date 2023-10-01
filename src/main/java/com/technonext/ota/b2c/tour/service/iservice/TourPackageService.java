package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.PackageDescriptionResponse;

public interface TourPackageService {
    PackageDescriptionResponse getTourPackageDescription(Integer tourPackageId);
}
