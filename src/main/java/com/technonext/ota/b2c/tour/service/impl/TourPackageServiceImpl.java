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
    public CompletableFuture<TourPackageResponse> getLocationWiseTourPackages(int locationId)
                    throws JsonProcessingException {

        CompletableFuture<List<PackageCategoryResponse>> categoryFuture = fetchPackageCategoriesAsync();
        CompletableFuture<PackagePriceLimitResponse> priceRangeFuture = fetchPackPriceRangeAsync(locationId);
        CompletableFuture<List<PackageTagResponse>> tagFuture = fetchPackageTagsAsync(locationId);
        CompletableFuture<List<TourPackageInfo>> packageResultFuture = fetchPackageResultsAsync(locationId);

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
    public CompletableFuture<List<TourPackageInfo>> fetchPackageResultsAsync(Integer locationId)
                throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        return CompletableFuture.completedFuture(objectMapper.readValue(
                tourPackageRepository.getLocationWiseTourPackages(locationId, baseUrl), new TypeReference<>() {}));
    }

    @Async
    public CompletableFuture<List<PackageCategoryResponse>> fetchPackageCategoriesAsync() {
        List<PackageCategoryResponse> categories =
                packageCategoryRepository
                        .findAllTourPackageCategory();
        return CompletableFuture.completedFuture(categories).exceptionally(ex -> Collections.emptyList());
    }

    @Async
    public CompletableFuture<List<PackageTagResponse>> fetchPackageTagsAsync(Integer locationId) {

        List<PackageTagResponse> tags = packageTagRepository.findPackageTagWithCountByLocationId(locationId);
        return CompletableFuture.completedFuture(tags).exceptionally(ex -> Collections.emptyList());
    }

    @Async
    public CompletableFuture<PackagePriceLimitResponse> fetchPackPriceRangeAsync(Integer locationId) {

        PackagePriceLimitResponse priceRange = tourPackageContentRepository.getPackagePriceLimitByLocation(locationId);
        return CompletableFuture.completedFuture(priceRange).exceptionally(ex -> new PackagePriceLimitResponse() {
            @Override
            public Double getMin() {
                return 500d;
            }

            @Override
            public Double getMax() {
                return 100000d;
            }
        });
    }
}
