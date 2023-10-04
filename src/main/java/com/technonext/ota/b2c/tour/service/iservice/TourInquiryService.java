package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.request.CustomTourInquiryRequest;

public interface TourInquiryService {
    void createCustomTourRequest(CustomTourInquiryRequest tourInquiryRequest);
}
