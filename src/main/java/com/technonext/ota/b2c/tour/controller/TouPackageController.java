package com.technonext.ota.b2c.tour.controller;


import com.technonext.ota.b2c.tour.dto.response.TourPackageDetailsResponse;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.TOUR_PACKAGE_ENDPOINT;

@RestController
@RequestMapping(TOUR_PACKAGE_ENDPOINT)
public class TouPackageController {

    private final TourPackageDetailsService tourPackageService;

    public TouPackageController(TourPackageDetailsService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @GetMapping("/details")
    public TourPackageDetailsResponse getPackageDetailsInfo(@RequestParam(name = "tourPackageId") Integer tourPackageId){
        try {
            return tourPackageService.getTourPackageDetailsInfo(tourPackageId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

}
