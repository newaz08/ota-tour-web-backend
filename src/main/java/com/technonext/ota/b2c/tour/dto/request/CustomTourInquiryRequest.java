package com.technonext.ota.b2c.tour.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record CustomTourInquiryRequest(
    @NotNull
    String destination,
    @NotNull
    String departure,
    @NotNull
    LocalDateTime date,
    String requirements,
    @NotNull
    String firstName,
    @NotNull
    String lastName,
    @NotNull
    String email,
    @NotNull
    String contact
) {
}
