package com.technonext.ota.b2c.tour.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record TourPackageInquiryRequest(
    @NotBlank
    String name,
    @NotBlank
    String email,
    @NotBlank
    String mobileNumber,
    String requirement,
    Integer noOfAdults,
    Integer noOfChilds,
    Integer noOfInfants,
    Long b2cUserId,
    Integer tourPackageId,
    Integer locationId,
    List<Integer> tourInquiryChildAges
) {
}
