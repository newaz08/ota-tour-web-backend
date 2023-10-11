package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.request.CustomTourInquiryRequest;
import com.technonext.ota.b2c.tour.dto.request.TourPackageInquiryRequest;

public interface TourInquiryService {
    void createCustomTourRequest(CustomTourInquiryRequest tourInquiryRequest);
    void createTourPackageInquiry(TourPackageInquiryRequest request);
}
