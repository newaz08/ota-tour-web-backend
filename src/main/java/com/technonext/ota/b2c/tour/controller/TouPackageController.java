package com.technonext.ota.b2c.tour.controller;


import com.technonext.ota.b2c.tour.dto.response.PackageDescriptionResponse;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.TOUR_PACKAGE_ENDPOINT;

@RestController
@RequestMapping(TOUR_PACKAGE_ENDPOINT)
public class TouPackageController {

    private final TourPackageService tourPackageService;

    public TouPackageController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @GetMapping("/details")
    public PackageDescriptionResponse getPackageDescription(@RequestParam(name = "tourPackageId") Integer tourPackageId){
        return tourPackageService.getTourPackageDescription(tourPackageId);
    }

}
