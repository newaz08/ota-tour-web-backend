package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.request.CustomTourInquiryRequest;
import com.technonext.ota.b2c.tour.model.entity.TourInquiry;
import com.technonext.ota.b2c.tour.model.enums.InquiryChannel;
import com.technonext.ota.b2c.tour.model.enums.InquiryFor;
import com.technonext.ota.b2c.tour.model.enums.InquiryStatus;
import com.technonext.ota.b2c.tour.model.enums.InquiryType;
import com.technonext.ota.b2c.tour.repository.TourInquiryRepository;
import com.technonext.ota.b2c.tour.service.iservice.TourInquiryService;
import com.technonext.ota.b2c.tour.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.technonext.ota.b2c.tour.constant.ApplicationConstant.CUSTOM_INQUIRY_INITIAL;

@Service
@RequiredArgsConstructor
public class TourInquiryServiceImpl implements TourInquiryService {
    private final TourInquiryRepository tourInquiryRepository;
    private final CommonUtil commonUtil;

    @Override
    public void createCustomTourRequest(CustomTourInquiryRequest tourInquiryRequest) {
        Long countOnDateOfInquiry = tourInquiryRepository.countByDateOfInquiryAfter(LocalDate.now().atStartOfDay());
        String inquiryNumber = commonUtil.generateUniqueNumber(CUSTOM_INQUIRY_INITIAL, countOnDateOfInquiry + 1, 5);
        TourInquiry tourInquiry = TourInquiry.builder()
            .inquiryChannel(InquiryChannel.MOBILE)
            .inquiryType(InquiryType.GENERAL)
            .inquiryFor(InquiryFor.TOUR_PACKAGES)
            .name(tourInquiryRequest.name())
            .dateOfInquiry(LocalDateTime.now())
            .preferredJourneyDate(tourInquiryRequest.date())
            .email(tourInquiryRequest.email())
            .mobileNumber(tourInquiryRequest.contact())
            .requirement(tourInquiryRequest.requirements())
            .inquiryLocation(tourInquiryRequest.destination())
            .customerLocation(tourInquiryRequest.departure())
            .inquiryStatus(InquiryStatus.NEW)
            .inquiryNumber(inquiryNumber)
             //TODO : cannot be parsed from the btc service
            .b2cUserId(1L)
            .build();
        tourInquiryRepository.save(tourInquiry);
    }
}
