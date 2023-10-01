package com.technonext.ota.b2c.tour.dto.response;

import java.time.LocalDateTime;

public interface PackageDescriptionResponse {
    String getPackageName();
    LocalDateTime getPackageEndDate();
    String getDisclaimer();
    String getPackageOverview();
    String getInclusion();
    String getExclusion();
    String getTravelTips();
    String getLocationMapLink();
}
