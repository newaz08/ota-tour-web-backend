package com.technonext.ota.b2c.tour.controller;

import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestContentProjection;
import com.technonext.ota.b2c.tour.service.iservice.PointsOfInterestContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.PONT_OF_INTEREST_CONTENT_ENDPOINT;

@RestController
@RequestMapping(PONT_OF_INTEREST_CONTENT_ENDPOINT)
@Tag(name = "PointsOfInterest Controller", description = "API Endpoints for PointsOfInterest Content related operations.")
public class PointsOfInterestContentController {
    private final PointsOfInterestContentService pointsOfInterestContentService;

    public PointsOfInterestContentController(PointsOfInterestContentService pointsOfInterestContentService) {
        this.pointsOfInterestContentService = pointsOfInterestContentService;
    }
    @GetMapping("/search")
    @Operation(summary = "API Endpoints for Tour PointsOfInterest Content searching")
    public ResponseEntity<List<PointsOfInterestContentProjection>> getPointsOfInterestContentById(
            @RequestParam(name="pointsOfInterestId") Long pointsOfInterestId){
        return new ResponseEntity<>(pointsOfInterestContentService.getPointsOfInterestContentById(
                                    pointsOfInterestId), HttpStatus.OK);
    }
}
