package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.LocationResponse;
import java.util.List;

public interface LocationService {
    List<LocationResponse> getLocationsName(String locationName);
}
