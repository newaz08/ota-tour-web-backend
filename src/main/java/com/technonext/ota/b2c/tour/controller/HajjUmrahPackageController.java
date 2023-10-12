package com.technonext.ota.b2c.tour.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technonext.ota.b2c.tour.dto.response.TourPackageDetailsResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageResponse;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageDetailsService;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.HAJJ_UMRAH_PACKAGE_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(HAJJ_UMRAH_PACKAGE_ENDPOINT)
@Tag(name = "Hajj Umrah Package Controller", description = "API Endpoints for Hajj/Umrah Package related operations.")
public class HajjUmrahPackageController {

    private final TourPackageService tourPackageService;
    private final TourPackageDetailsService tourPackageDetailsService;
    @GetMapping("/search")
    @Operation(summary = "API Endpoint for Hajj Umrah Package Searching")
    public TourPackageResponse getHajjUmarhPackages() {

        try {
            //tourPackageService.getHajjUmrahOrTourPackageIds(true);
            return tourPackageService.getLocationOrPackageWiseTourOrHajjUmarhPackages(
                        null, tourPackageService.getHajjUmrahOrTourPackageIds(true),
                                true).get();
        } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Package Not Found");
        }

    }

    @GetMapping("/details")
    @Operation(summary = "API Endpoint for Hajj Umrah Package Details View")
    public TourPackageDetailsResponse getPackageDetailsInfo(@RequestParam(name = "tourPackageId") Integer tourPackageId){
        try {
            return tourPackageDetailsService.getTourPackageDetailsInfo(tourPackageId).get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("No Data Found");
        }

    }
}
