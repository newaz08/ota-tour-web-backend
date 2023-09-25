package com.technonext.ota.b2c.tour.mapper;

import com.technonext.ota.b2c.tour.dto.response.TourPackageContentProjection;
import com.technonext.ota.b2c.tour.dto.response.TourPackageContentResponse;

import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface TourPackageContentMapper {
    List<TourPackageContentResponse> toTourPackageContentResponseList(List<TourPackageContentProjection> tourPackageContentList);

}
