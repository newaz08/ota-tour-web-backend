package com.technonext.ota.b2c.tour.controller;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.TOUR_PACKAGE_ENDPOINT;

@RestController
@RequestMapping(TOUR_PACKAGE_ENDPOINT)
public class TourPackageController {


    @Autowired
    private TourPackageService tourPackageService;

    @GetMapping("/price-limit")
    public PackagePriceLimitResponse packagePriceLimitByLocation(@RequestParam(name = "locationId") Integer locationId){
        return tourPackageService.getPackagePriceLimit(locationId);
    }

}
