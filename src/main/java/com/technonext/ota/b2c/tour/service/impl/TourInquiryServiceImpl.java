package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.request.CustomTourInquiryRequest;
import com.technonext.ota.b2c.tour.model.entity.TourInquiry;
import com.technonext.ota.b2c.tour.model.enums.InquiryChannel;
import com.technonext.ota.b2c.tour.model.enums.InquiryFor;
import com.technonext.ota.b2c.tour.model.enums.InquiryStatus;
import com.technonext.ota.b2c.tour.model.enums.InquiryType;
import com.technonext.ota.b2c.tour.repository.TourInquiryRepository;
import com.technonext.ota.b2c.tour.service.iservice.TourInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TourInquiryServiceImpl implements TourInquiryService {
    private final TourInquiryRepository tourInquiryRepository;

    @Override
    public void createCustomTourRequest(CustomTourInquiryRequest tourInquiryRequest) {
        TourInquiry tourInquiry = TourInquiry.builder()
            .inquiryChannel(InquiryChannel.WEB)
            .inquiryType(InquiryType.GENERAL)
            .inquiryFor(InquiryFor.Tour_Packages)
            .name(tourInquiryRequest.firstName() + " " + tourInquiryRequest.lastName())
            .dateOfInquiry(new Date(tourInquiryRequest.date()))
            .email(tourInquiryRequest.email())
            .mobileNumber(tourInquiryRequest.contact())
            .requirement(tourInquiryRequest.requirements())
            .inquiryLocation(tourInquiryRequest.destination())
            .customerLocation(tourInquiryRequest.departure())
            .inquiryStatus(InquiryStatus.NEW_INQUIRY)
            .b2cUserId(1L)
            .build();
        tourInquiryRepository.save(tourInquiry);
    }
}
