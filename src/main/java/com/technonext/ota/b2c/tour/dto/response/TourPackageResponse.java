package com.technonext.ota.b2c.tour.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourPackageResponse {

    private List<PackageCategoryResponse> categoryList;
    private PackagePriceLimitResponse priceRange;
    private List<PackageTagResponse> tagList;
    private List<TourPackageInfo> packageResult;
    private int totalResult;

}
