package com.technonext.ota.b2c.tour.service.iservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technonext.ota.b2c.tour.dto.response.TourPackageResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TourPackageService {
    CompletableFuture<TourPackageResponse> getLocationOrPackageWiseTourOrHajjUmarhPackages(
            Integer locationId, List<Integer> packageIds, boolean isForHajjUmrah) throws JsonProcessingException;

    List<Integer> getHajjUmrahOrTourPackageIds(boolean isForHajjUmrah);
}
