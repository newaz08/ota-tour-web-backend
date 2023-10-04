package com.technonext.ota.b2c.tour.constant;

public class APIEndpointConstants {

    private APIEndpointConstants() {
    }
    private static final String API_VERSION = "/api/v1";
    public static final String AUTHENTICATION_ENDPOINT = API_VERSION + "/auth";
    public static final String LOCATION_ENDPOINT= API_VERSION + "/location";
    public static final String PACKAGE_TAG_ENDPOINT= API_VERSION + "/package-tag";
    public static final String TOUR_PACKAGE_ENDPOINT= API_VERSION + "/tour/package";
    public static final String TOUR_INQUIRY_ENDPOINT= API_VERSION + "/tour/inquiry";
    public static final String TOUR_PACKAGE_CONTENT_ENDPOINT= API_VERSION + "/tourPackageContent";

    public static final String PACKAGE_CATEGORY_ENDPOINT= API_VERSION + "/packageCategory";

    public static final String PointsOfInterest_CONTENT_ENDPOINT = API_VERSION + "/PointsOfInterestContent";

}
