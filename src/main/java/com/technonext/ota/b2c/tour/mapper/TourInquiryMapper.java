package com.technonext.ota.b2c.tour.mapper;

import com.technonext.ota.b2c.tour.dto.request.TourPackageInquiryRequest;
import com.technonext.ota.b2c.tour.model.entity.TourInquiry;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface TourInquiryMapper {

    TourInquiry toTourPackageInquiryEntity(TourPackageInquiryRequest request);
}
