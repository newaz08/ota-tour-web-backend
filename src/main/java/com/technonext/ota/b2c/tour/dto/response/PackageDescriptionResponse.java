package com.technonext.ota.b2c.tour.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDescriptionResponse {

    private LocalDateTime packageEndDate;
    private String desClaimer;
    private String packageOverview;
    private String inclusion;
    private String exclusion;
    private String locationMapLink;
    private List<PointsOfInterestProjection> pointOfInterest;
    private String travelTips;
}
