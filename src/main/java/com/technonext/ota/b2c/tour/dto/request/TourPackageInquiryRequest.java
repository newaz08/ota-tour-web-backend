package com.technonext.ota.b2c.tour.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record TourPackageInquiryRequest(
    @NotBlank
    String name,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String mobileNumber,
    String requirement,
    Integer noOfAdults,
    Integer noOfChilds,
    Integer noOfInfants,
    String inquiryLocation,
    Integer tourPackageId,
    Integer locationId,
    List<Integer> tourInquiryChildAges
) {
}
