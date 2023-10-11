package com.technonext.ota.b2c.tour.dto.response;

import java.util.List;

public interface NextTripTagWithPackageInfoProjection {
    String getCountryName();
    String getCountryId();
    String getBasePrice();
    List<String> getTagList();
}
