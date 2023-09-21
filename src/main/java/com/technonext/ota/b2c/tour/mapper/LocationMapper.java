package com.technonext.ota.b2c.tour.mapper;

import com.technonext.ota.b2c.tour.dto.response.LocationResponse;
import com.technonext.ota.b2c.tour.model.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    List<LocationResponse> toLocationResponseList(List<Location> locationList);

    LocationResponse toLocationResponse(Location location);

}
