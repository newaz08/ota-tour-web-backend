package com.technonext.ota.b2c.tour.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourPackagePriceRange {

    private double min;
    private double max;
}
