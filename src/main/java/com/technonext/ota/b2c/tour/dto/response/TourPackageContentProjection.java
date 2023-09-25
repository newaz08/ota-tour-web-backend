package com.technonext.ota.b2c.tour.dto.response;

public interface TourPackageContentProjection {
    Byte getContentType();
    String getMimeType();
    String getPath();
}
