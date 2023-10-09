package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.constant.ApplicationConstant;
import com.technonext.ota.b2c.tour.dto.response.*;
import com.technonext.ota.b2c.tour.model.entity.TourPackage;
import com.technonext.ota.b2c.tour.repository.PackageTagRepository;
import com.technonext.ota.b2c.tour.repository.TourPackageRepository;
import com.technonext.ota.b2c.tour.service.iservice.PointsOfInterestContentService;
import com.technonext.ota.b2c.tour.service.iservice.TermsPolicyService;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageContentService;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TourPackageDetailsServiceImpl implements TourPackageDetailsService {

    private final TourPackageRepository tourPackageRepository;
    private final TourPackageContentService tourPackageContentService;
    private final PointsOfInterestContentService pointsOfInterestContentService;
    private final TermsPolicyService termsPolicyService;
    private final PackageTagRepository packageTagRepository;
    @Override
    public CompletableFuture<TourPackageDetailsResponse> getTourPackageDetailsInfo(Integer tourPackageId) {

        TourPackage tourPackage = tourPackageRepository.findById(tourPackageId).orElse(new TourPackage());
        CompletableFuture<List<TourPackageContentResponse>> tourPackageContentFuture =
                                                getTourPackageContent(tourPackageId);

        CompletableFuture<PackageDetailsProjection> packageDetailsFuture =
                getTourPackageDetails(tourPackageId);


        CompletableFuture<List<PointsOfInterestProjection>>
                    pointOfInterestFuture = getTourPackagePointOfInterest(tourPackage.getLocation().getId());

        CompletableFuture<List<TourPackageTagProjection>> tourPackageTagListFuture = getTourPackageTagList(tourPackageId);
        CompletableFuture<List<TermsPolicyResponse>>  policiesFuture = getTermPolicy(tourPackageId);

        return CompletableFuture.allOf(tourPackageContentFuture, packageDetailsFuture, tourPackageTagListFuture,
                                                                policiesFuture)
                .thenApplyAsync(tourDetails -> {
                    PackageDetailsProjection packageDetailsProjection = packageDetailsFuture.join();
                    PackageDescriptionResponse descriptionResponse =
                            PackageDescriptionResponse.builder()
                                    .packageName(packageDetailsProjection.getPackageName())
                                    .noOfDays(packageDetailsProjection.getNoOfDays())
                                    .noOfNights(packageDetailsProjection.getNoOfNights())
                                    .noOfPeopleForDisplay(packageDetailsProjection.getNoOfPeopleForDisplay())
                                    .basePrice(packageDetailsProjection.getBasePrice())
                                    .cancellationText(packageDetailsProjection.getCancellationText())
                                    .suitableFor(packageDetailsProjection.getSuitableFor())
                                    .generalDiscountPercentage(packageDetailsProjection.getGeneralDiscountPercentage())
                                    .packageEndDate(packageDetailsProjection.getPackageEndDate())
                                    .packageOverview(packageDetailsProjection.getPackageOverview())
                                    .desClaimer(packageDetailsProjection.getDisclaimer())
                                    .inclusion(packageDetailsProjection.getInclusion())
                                    .exclusion(packageDetailsProjection.getExclusion())
                                    .locationMapLink(packageDetailsProjection.getLocationMapLink())
                                    .pointOfInterest(pointOfInterestFuture.join())
                                    .travelTips(packageDetailsProjection.getTravelTips())
                                               .build();

                    return TourPackageDetailsResponse.builder()
                                                     .packageTags(tourPackageTagListFuture.join())
                                                     .packageContent(tourPackageContentFuture.join())
                                                     .packageDetails(descriptionResponse)
                                                     .termsPolicies(policiesFuture.join())
                            .build();
                });
    }

    @Async
    CompletableFuture<List<TourPackageContentResponse>> getTourPackageContent(Integer tourPackageId) {

        return CompletableFuture.completedFuture(
                tourPackageContentService.getTourPackageContentByPackageId(tourPackageId))
                            .exceptionally(ex-> Collections.emptyList())   ;
    }

    @Async
    CompletableFuture<PackageDetailsProjection> getTourPackageDetails(Integer tourPackageId) {

        return CompletableFuture.completedFuture(tourPackageRepository.getTourPackageDetailsById(tourPackageId));
    }

    @Async
    CompletableFuture<List<PointsOfInterestProjection>> getTourPackagePointOfInterest(Integer locationId) {

        return CompletableFuture.completedFuture(
                pointsOfInterestContentService.getPackagePointOfInterest(locationId))
                .exceptionally(ex -> Collections.emptyList());
    }

    @Async
    CompletableFuture<List<TourPackageTagProjection>> getTourPackageTagList(Integer tourPackageId) {

        return CompletableFuture.completedFuture(
                packageTagRepository.getPackageTagListByPackageId(tourPackageId))
                .exceptionally(ex -> Collections.emptyList());
    }

    @Async
    CompletableFuture<List<TermsPolicyResponse>> getTermPolicy(Integer tourPackageId) {
        List<TermsPolicyResponse> termsPolicyResponses = new ArrayList<>();

        TermsPolicyResponse termsPolicyResponse = new TermsPolicyResponse();
        termsPolicyResponse.setPolicyName(ApplicationConstant.CHILD_POLICY_NAME);
        termsPolicyResponse.setPolicies(termsPolicyService.getChildPolicies(tourPackageId));
        termsPolicyResponses.add(termsPolicyResponse);

        termsPolicyResponse = new TermsPolicyResponse();
        termsPolicyResponse.setPolicyName(ApplicationConstant.REFUND_POLICY_NAME);
        termsPolicyResponse.setPolicies(termsPolicyService.getRefundPolicies(tourPackageId));
        termsPolicyResponses.add(termsPolicyResponse);

        termsPolicyResponse = new TermsPolicyResponse();
        termsPolicyResponse.setPolicyName(ApplicationConstant.OTHER_POLICY_NAME);
        termsPolicyResponse.setPolicies(Arrays.asList(termsPolicyService.getOtherPolicy(tourPackageId)));
        termsPolicyResponses.add(termsPolicyResponse);

        return CompletableFuture.completedFuture(termsPolicyResponses).exceptionally(ex -> Collections.emptyList());
    }

}
