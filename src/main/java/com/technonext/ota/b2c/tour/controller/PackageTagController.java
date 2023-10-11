package com.technonext.ota.b2c.tour.controller;

import com.technonext.ota.b2c.tour.dto.response.NextTripTagWithPackageInfoResponse;
import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageTagProjection;
import com.technonext.ota.b2c.tour.service.iservice.PackageTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.PACKAGE_TAG_ENDPOINT;

@RestController
@RequestMapping(PACKAGE_TAG_ENDPOINT)
@RequiredArgsConstructor
@Tag(name = "Package Tag Controller", description = "API Endpoints for Package Tag related operations.")
public class PackageTagController {
    private final PackageTagService packageTagService;

    @GetMapping()
    public List<PackageTagResponse> tagResponsesByLocation(@RequestParam(name = "locationId") Integer locationId){
        return packageTagService.getPackageTagsByLocation(locationId);
    }

    @GetMapping("/next-trip-tag-with-package-info")
    @Operation(summary = "API Endpoint for Next Trip Tag With Package Info")
    public NextTripTagWithPackageInfoResponse getNextTripTagWithPackageInfoGroupByCountry(){
        return packageTagService.getNextTripTagWithPackageInfoGroupByCountry();
    }
    @GetMapping("/package-wise-tag-list")
    @Operation(summary = "API Endpoints for Package Tag List")
    public List<TourPackageTagProjection> getPackageTagList(@RequestParam(name = "tourPackageId")Integer tourPackageId){
        return packageTagService.getPackageTagsByTourPackageId(tourPackageId);
    }
}
