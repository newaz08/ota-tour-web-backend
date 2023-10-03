package com.technonext.ota.b2c.tour.controller;

import com.technonext.ota.b2c.tour.dto.response.LocationResponse;
import com.technonext.ota.b2c.tour.service.iservice.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.LOCATION_ENDPOINT;
@RestController
@RequiredArgsConstructor
@RequestMapping(LOCATION_ENDPOINT)
@Tag(name = "Location Controller", description = "API Endpoints for location related operations.")
public class LocationController {

    private final LocationService locationService;
    @GetMapping ("/search")
    @Operation(summary = "API Endpoints for location searching")
    public ResponseEntity<List<LocationResponse>> search(@RequestParam(name="locationName") String locationName){
        return new ResponseEntity<>(locationService.getLocations(locationName), HttpStatus.OK);
    }

}
