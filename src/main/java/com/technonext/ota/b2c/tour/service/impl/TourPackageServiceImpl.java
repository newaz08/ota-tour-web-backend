package com.technonext.ota.b2c.tour.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technonext.ota.b2c.tour.dto.response.*;
import com.technonext.ota.b2c.tour.repository.PackageCategoryRepository;
import com.technonext.ota.b2c.tour.repository.PackageTagRepository;
import com.technonext.ota.b2c.tour.repository.TourPackageContentRepository;
import com.technonext.ota.b2c.tour.repository.TourPackageRepository;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class TourPackageServiceImpl implements TourPackageService {

    @Value("${application.base-url}")
    private String baseUrl;
    private final TourPackageRepository tourPackageRepository;
    private final PackageCategoryRepository packageCategoryRepository;
    private final PackageTagRepository packageTagRepository;
    private final TourPackageContentRepository tourPackageContentRepository;

    @Override
    public CompletableFuture<TourPackageResponse> getLocationOrPackageWiseTourOrHajjUmarhPackages(
            Integer locationId, List<Integer> packageIds, boolean isForHajjUmrah)
                    throws JsonProcessingException {

        CompletableFuture<List<PackageCategoryResponse>> categoryFuture = fetchPackageCategoriesAsync(isForHajjUmrah);
        CompletableFuture<PackagePriceLimitResponse> priceRangeFuture = fetchPackPriceRangeAsync(
                locationId,packageIds,isForHajjUmrah);
        CompletableFuture<List<PackageTagResponse>> tagFuture = fetchPackageTagsAsync(
                locationId,packageIds,isForHajjUmrah);
        CompletableFuture<List<TourPackageInfo>> packageResultFuture = fetchPackageResultsAsync(
                locationId,packageIds,isForHajjUmrah);

        return CompletableFuture.allOf(categoryFuture, priceRangeFuture, tagFuture, packageResultFuture)
                .thenApplyAsync(ignoredVoid -> {

                    List<TourPackageInfo> sortedTourPackages = new ArrayList<>(packageResultFuture.join());
                    Collections.sort(sortedTourPackages);

                    return TourPackageResponse.builder()
                                              .categoryList(categoryFuture.join())
                                              .priceRange(priceRangeFuture.join())
                                              .tagList(tagFuture.join())
                                              .packageResult(sortedTourPackages)
                                              .totalResult(sortedTourPackages.size())
                                              .build();

                });
    }

    @Async
    public CompletableFuture<List<TourPackageInfo>> fetchPackageResultsAsync(
            Integer locationId,List<Integer> packageIds, boolean isForHajjUmrah)
                throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        int isNull = packageIds == null ? 1 : 0;
        List<TourPackageInfo> tourPackageInfos = objectMapper.readValue(
                tourPackageRepository.getLocationWiseTourPackages(
                        locationId, baseUrl,packageIds,isForHajjUmrah,isNull), new TypeReference<>() {});
        tourPackageInfos = tourPackageInfos == null ? Collections.emptyList() : tourPackageInfos;
        return CompletableFuture.completedFuture(tourPackageInfos)
                .exceptionally(ex -> Collections.emptyList());
    }

    @Async
    public CompletableFuture<List<PackageCategoryResponse>> fetchPackageCategoriesAsync(boolean isForHajjUmrah) {
        List<PackageCategoryResponse> categories =
                packageCategoryRepository
                        .findAllPackageCategory(isForHajjUmrah);
        return CompletableFuture.completedFuture(categories).exceptionally(ex -> Collections.emptyList());
    }

    @Async
    public CompletableFuture<List<PackageTagResponse>> fetchPackageTagsAsync(
                            Integer locationId,List<Integer> packageIds, boolean isForHajjUmrah) {
        int isNull = packageIds == null ? 1 : 0;
        List<PackageTagResponse> tags = packageTagRepository.findPackageTagWithCountByLocationId(
                locationId,packageIds,isNull, isForHajjUmrah);
        return CompletableFuture.completedFuture(tags).exceptionally(ex -> Collections.emptyList());
    }

    @Async
    public CompletableFuture<PackagePriceLimitResponse> fetchPackPriceRangeAsync(
            Integer locationId, List<Integer> packageIds, boolean isForHajjUmrah) {
        int isNull = packageIds == null ? 1 : 0;
        PackagePriceLimitResponse priceRange = tourPackageContentRepository.getPackagePriceLimitByLocation(
                                locationId,packageIds, isNull, isForHajjUmrah);
        return CompletableFuture.completedFuture(priceRange).exceptionally(ex -> new PackagePriceLimitResponse() {
            @Override
            public Double getMin() {
                return 100d;
            }

            @Override
            public Double getMax() {
                return 100000d;
            }
        });
    }

    @Override
    public List<Integer> getHajjUmrahOrTourPackageIds(boolean isForHajjUmrah) {
        return tourPackageRepository.getHajjUmrahOrTourPackageIds(isForHajjUmrah);
    }
}
