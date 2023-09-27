package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.LocationResponse;
import com.technonext.ota.b2c.tour.mapper.LocationMapper;
import com.technonext.ota.b2c.tour.model.entity.Location;
import com.technonext.ota.b2c.tour.repository.LocationRepository;
import com.technonext.ota.b2c.tour.service.iservice.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;

    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public List<LocationResponse> getLocations(String locationName) {
       List<Location> locations = locationRepository.findByLocation(locationName);
       return locationMapper.toLocationResponseList(locations);
    }
}
