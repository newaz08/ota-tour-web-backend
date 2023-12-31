package com.technonext.ota.b2c.tour.controller;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageContentResponse;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.TOUR_PACKAGE_CONTENT_ENDPOINT;

@RestController
@RequestMapping(TOUR_PACKAGE_CONTENT_ENDPOINT)
@RequiredArgsConstructor
@Tag(name = "Tour Package Content Controller", description = "API Endpoints for Tour Package Content related operations.")
public class TourPackageContentController {
    private final TourPackageContentService tourPackageContentService;

    @GetMapping("/search")
    @Operation(summary = "API Endpoints for Tour Package Content searching")
    public ResponseEntity<List<TourPackageContentResponse>> getTourPackageContentSearch(
        @RequestParam(name = "id") Integer tourPackageId) {
        return new ResponseEntity<>(tourPackageContentService.getTourPackageContentByPackageId(tourPackageId),
            HttpStatus.OK);
    }

    @GetMapping("/price-limit")
    public PackagePriceLimitResponse packagePriceLimitByLocation(
        @RequestParam(name = "locationId") Integer locationId) {
        return tourPackageContentService.getPackagePriceLimit(locationId);
    }

}
