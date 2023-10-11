package com.technonext.ota.b2c.tour.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record CustomTourInquiryRequest(
    @NotNull
    @NotEmpty
    String destination,
    @NotNull
    @NotEmpty
    String departure,
    @NotNull
    LocalDateTime date,
    String requirements,
    @NotNull
    @NotEmpty
    String firstName,
    @NotNull
    @NotEmpty
    String lastName,
    @NotNull
    @Email
    String email,
    @NotNull
    String contact
) {
}
