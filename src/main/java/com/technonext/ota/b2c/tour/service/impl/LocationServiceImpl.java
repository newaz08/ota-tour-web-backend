package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.LocationResponse;
import com.technonext.ota.b2c.tour.model.entity.Location;
import com.technonext.ota.b2c.tour.repository.LocationRepository;
import com.technonext.ota.b2c.tour.service.iservice.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.technonext.ota.b2c.tour.constant.ApplicationConstant.DEFAULT_PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;

    @Override
    public List<LocationResponse> getLocationsName(String locationName) {
        Pageable pageable = PageRequest.ofSize(DEFAULT_PAGE_SIZE);
        Page<Location> locations = locationRepository.findByLocationName(locationName,pageable);
        return locations.getContent().stream()
                .map(location -> new LocationResponse(
                        location.getId(),
                        location.getLocationName()
                        )
                ).toList();
    }

}
