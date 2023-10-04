package com.technonext.ota.b2c.tour.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technonext.ota.b2c.tour.dto.response.TourPackageDetailsResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageResponse;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageDetailsService;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.TOUR_PACKAGE_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(TOUR_PACKAGE_ENDPOINT)
@Tag(name = "Tour Package Controller", description = "API Endpoints for Tour Package related operations.")
public class TourPackageController {

    private final TourPackageService tourPackageService;
    private final TourPackageDetailsService tourPackageDetailsService;
    @GetMapping("/search/{locationId}")
    @Operation(summary = "API Endpoint for Tour Package Searching")
    public TourPackageResponse getTourPackages(@PathVariable Integer locationId) {

        try {
            return tourPackageService.getLocationWiseTourPackages(locationId).get();
        } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Package Not Found");
        }

    }

    @GetMapping("/details")
    @Operation(summary = "API Endpoint for Tour Package Details View")
    public TourPackageDetailsResponse getPackageDetailsInfo(@RequestParam(name = "tourPackageId") Integer tourPackageId){
        try {
            return tourPackageDetailsService.getTourPackageDetailsInfo(tourPackageId).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("No Data Found");
        }

    }

}
