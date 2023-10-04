package com.technonext.ota.b2c.tour.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourPackagePrice {

    private boolean isDiscounted;
    private float discountPercent;
    private double basePrice;
    private String brochurePath;


}
