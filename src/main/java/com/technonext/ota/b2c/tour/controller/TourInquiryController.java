package com.technonext.ota.b2c.tour.controller;

import com.technonext.ota.b2c.tour.dto.request.CustomTourInquiryRequest;
import com.technonext.ota.b2c.tour.service.iservice.TourInquiryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.TOUR_INQUIRY_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(TOUR_INQUIRY_ENDPOINT)
@Tag(name = "Tour Inquiry Controller", description = "API Endpoints for tour inquiry related operations.")
public class TourInquiryController {

    private final TourInquiryService tourInquiryService;
    @PostMapping("/new/request")
    public void createCustomTourRequest(CustomTourInquiryRequest tourInquiryRequest) {
        tourInquiryService.createCustomTourRequest(tourInquiryRequest);
    }
}
