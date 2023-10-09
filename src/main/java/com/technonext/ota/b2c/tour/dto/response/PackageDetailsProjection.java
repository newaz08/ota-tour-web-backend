package com.technonext.ota.b2c.tour.dto.response;

import com.technonext.ota.b2c.tour.model.enums.SuitableFor;

import java.time.LocalDateTime;

public interface PackageDetailsProjection {
    String getPackageName();
    String getNoOfNights();
    String getNoOfDays();
    String getNoOfPeopleForDisplay();
    Double getBasePrice();
    String getSuitableFor();
    Double getGeneralDiscountPercentage();
    String getPackageEndDate();
    String getDisclaimer();
    String getPackageOverview();
    String getInclusion();
    String getExclusion();
    String getTravelTips();
    String getLocationMapLink();
    String getCancellationText();

}
