package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.TourPackageDetailsResponse;

import java.util.concurrent.CompletableFuture;

public interface TourPackageDetailsService {
    CompletableFuture<TourPackageDetailsResponse> getTourPackageDetailsInfo(Integer tourPackageId);
}
