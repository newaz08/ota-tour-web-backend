package com.technonext.ota.b2c.tour.mapper;

import com.technonext.ota.b2c.tour.dto.response.LocationResponse;
import com.technonext.ota.b2c.tour.model.entity.Location;
import org.mapstruct.Mapper;


import java.util.List;


@Mapper(componentModel = "spring")
public interface LocationMapper {
    List<LocationResponse> toLocationResponseList(List<Location> locationList);

}
