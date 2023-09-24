package com.technonext.ota.b2c.tour.controller;

import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.service.iservice.PackageTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.PACKAGE_TAG_ENDPOINT;

@RestController
@RequestMapping(PACKAGE_TAG_ENDPOINT)
public class PackageTagController {

    @Autowired
    private PackageTagService packageTagService;

    @GetMapping()
    public List<PackageTagResponse> tagResponsesByLocation(@RequestParam(name = "locationId") Integer locationId){
        return packageTagService.getPackageTagsByLocation(locationId);
    }
}
