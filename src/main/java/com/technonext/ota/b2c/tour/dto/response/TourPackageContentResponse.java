package com.technonext.ota.b2c.tour.dto.response;

public interface TourPackageContentResponse {
    Byte getContentType();
    String getMimeType();
    String getPath();
}
