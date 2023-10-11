package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.request.CustomTourInquiryRequest;
import com.technonext.ota.b2c.tour.dto.request.TourPackageInquiryRequest;
import com.technonext.ota.b2c.tour.mapper.TourInquiryMapper;
import com.technonext.ota.b2c.tour.model.entity.TourInquiry;
import com.technonext.ota.b2c.tour.model.entity.TourInquiryChildAge;
import com.technonext.ota.b2c.tour.model.enums.InquiryChannel;
import com.technonext.ota.b2c.tour.model.enums.InquiryFor;
import com.technonext.ota.b2c.tour.model.enums.InquiryStatus;
import com.technonext.ota.b2c.tour.model.enums.InquiryType;
import com.technonext.ota.b2c.tour.repository.LocationRepository;
import com.technonext.ota.b2c.tour.repository.TourInquiryChildAgeRepository;
import com.technonext.ota.b2c.tour.repository.TourInquiryRepository;
import com.technonext.ota.b2c.tour.repository.TourPackageRepository;
import com.technonext.ota.b2c.tour.service.iservice.TourInquiryService;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.technonext.ota.b2c.tour.util.CommonUtil;

import java.time.LocalDate;

import static com.technonext.ota.b2c.tour.constant.ApplicationConstant.CUSTOM_INQUIRY_INITIAL;
import static com.technonext.ota.b2c.tour.constant.ApplicationConstant.TOUR_INQUIRY_INITIAL;

@Service
@RequiredArgsConstructor
public class TourInquiryServiceImpl implements TourInquiryService {
    private final TourInquiryRepository tourInquiryRepository;
    private final TourInquiryChildAgeRepository childAgeRepository;
    private final LocationRepository locationRepository;
    private final TourPackageRepository tourPackageRepository;
    private final TourInquiryMapper mapper;
    private final CommonUtil commonUtil;

    @Override
    public void createCustomTourRequest(CustomTourInquiryRequest tourInquiryRequest) {
        Long countOnDateOfInquiry = tourInquiryRepository.countByDateOfInquiryAfter(LocalDate.now().atStartOfDay());
        String inquiryNumber = commonUtil.generateUniqueNumber(CUSTOM_INQUIRY_INITIAL, countOnDateOfInquiry + 1, 5);
        String tourInquiryNumber = commonUtil.generateUniqueNumber(TOUR_INQUIRY_INITIAL , countOnDateOfInquiry + 1,5);
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
//            .b2cUserId(1L)
            .inquiryStatus(InquiryStatus.NEW)
            .inquiryNumber(inquiryNumber)
             //TODO : cannot be parsed from the btc service
            .b2cUserId(1L)
            .build();
        tourInquiryRepository.save(tourInquiry);
    }
    @Override
    public void createTourPackageInquiry(TourPackageInquiryRequest request) {
        Long countOnDateOfInquiry = tourInquiryRepository.countByDateOfInquiryAfter(LocalDate.now().atStartOfDay());
        String tourInquiryNumber = commonUtil.generateUniqueNumber(TOUR_INQUIRY_INITIAL , countOnDateOfInquiry + 1,5);
//        Location location = locationRepository.findById(request.locationId())
//            .orElseThrow(() -> new ResourceNotFoundException(ExceptionConstant.LOCATION_NOT_FOUND));
//
//        TourPackage tourPackage = tourPackageRepository.findById(request.tourPackageId())
//            .orElseThrow(() -> new ResourceNotFoundException(ExceptionConstant.TOUR_PACKAGE_NOT_FOUND));

        TourInquiry tourInquiry = mapper.toTourPackageInquiryEntity(request);
        tourInquiry.setNoOfTravellers(request.noOfAdults() + request.noOfChilds() + request.noOfInfants());
        tourInquiry.setDateOfInquiry(LocalDateTime.now());
        tourInquiry.setInquiryChannel(InquiryChannel.WEB);
        tourInquiry.setInquiryFor(InquiryFor.TOUR_PACKAGES);
        tourInquiry.setInquiryType(InquiryType.TOUR_SPECIFIC);
        tourInquiry.setInquiryStatus(InquiryStatus.NEW);
        // TODO: will be implemented when get location information
        tourInquiry.setInquiryLocation("Tanguar Haoar");
        tourInquiry.setInquiryNumber(tourInquiryNumber);
        tourInquiry.setB2cUserId(1L);
//        tourInquiry.setLocationId(request.locationId());
//        tourInquiry.setTourPackageId(request.tourPackageId());
        tourInquiryRepository.save(tourInquiry);

        if(Objects.nonNull(request.tourInquiryChildAges())){
            List<TourInquiryChildAge> childAgeList = request.tourInquiryChildAges().stream()
                .map(inquiryChileAge -> TourInquiryChildAge
                    .builder()
                    .childAge(inquiryChileAge)
                    .tourInquiry(tourInquiry)
                    .build())
                .toList();
            childAgeRepository.saveAll(childAgeList);
        }
    }
}
