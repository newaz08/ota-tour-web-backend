package com.technonext.ota.b2c.tour.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourPackageDetailsResponse {

    private List<TourPackageContentResponse> packageContent;
    private PackageDescriptionResponse packageDetails;
    private List<TermsPolicyResponse> termsPolicies;

}
