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
public class NextTripTagWithPackageInfoResponse {

    List<PackageTagResponse> nextTripTag;
    List<NextTripTagWithPackageInfoProjection> nextTripTagWithPackageInfo;
}
