package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.NextTripTagWithPackageInfoProjection;
import com.technonext.ota.b2c.tour.dto.response.NextTripTagWithPackageInfoResponse;
import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageTagProjection;
import com.technonext.ota.b2c.tour.repository.PackageTagRepository;
import com.technonext.ota.b2c.tour.service.iservice.PackageTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PackageTagServiceImpl implements PackageTagService {

    private final PackageTagRepository packageTagRepository;
    @Override
    public List<PackageTagResponse> getPackageTagsByLocation(Integer locationId) {
        return packageTagRepository.findPackageTagWithCountByLocationId(
                locationId,null,1, false);
    }

    @Override
    public NextTripTagWithPackageInfoResponse getNextTripTagWithPackageInfoGroupByCountry() {

        List<PackageTagResponse> packageTagResponses = getTopFiveNextTripTag();
        List<Integer> packageTagIds = packageTagResponses.stream()
                .map(PackageTagResponse::getId)
                .toList();

        List<NextTripTagWithPackageInfoProjection> nextTripTagWithPackageInfoProjections =
                packageTagRepository.getNextTripTagWithPackageInfoGroupByCountry(packageTagIds);

        return NextTripTagWithPackageInfoResponse.builder()
                .nextTripTag(packageTagResponses)
                .nextTripTagWithPackageInfo(nextTripTagWithPackageInfoProjections)
                .build();

    }

    @Override
    public List<PackageTagResponse> getTopFiveNextTripTag() {
        return packageTagRepository.getTopFiveNextTripTag();
    }

    @Override
    public List<TourPackageTagProjection> getPackageTagsByTourPackageId(Integer tourPackageId) {
        return packageTagRepository.getPackageTagListByPackageId(tourPackageId);
    }

}
