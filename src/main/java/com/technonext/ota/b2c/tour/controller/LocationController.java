package com.technonext.ota.b2c.tour.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.LOCATION_ENDPOINT;
@RestController
@RequiredArgsConstructor
@RequestMapping(LOCATION_ENDPOINT)
@Tag(name = "Location Controller", description = "API Endpoints for location related operations.")
public class LocationController {
}
