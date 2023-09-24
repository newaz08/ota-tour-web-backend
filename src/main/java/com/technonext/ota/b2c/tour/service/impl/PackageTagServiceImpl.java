package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.repository.PackageTagRepository;
import com.technonext.ota.b2c.tour.service.iservice.PackageTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageTagServiceImpl implements PackageTagService {

    @Autowired
    private PackageTagRepository packageTagRepository;
    @Override
    public List<PackageTagResponse> getPackageTagsByLocation(Integer locationId) {
        return packageTagRepository.findPackageTagWithCountByLocationId(locationId);
    }
}
