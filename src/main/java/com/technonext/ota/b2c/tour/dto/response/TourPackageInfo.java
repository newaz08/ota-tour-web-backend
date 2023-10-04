package com.technonext.ota.b2c.tour.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import static com.technonext.ota.b2c.tour.constant.ApplicationConstant.PACKAGE_DEFAULT_CURRENCY;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourPackageInfo implements Comparable<TourPackageInfo>{
    private Integer id;
    private String title;
    private String country;
    private String city;
    private String suitableFor;
    private String cancellationText;
    private String noOfPeopleForDisplay;
    private Double duration;
    private String durationText;
    private String featuredPhoto;
    private String packageStart;
    private String packageEnd;
    private boolean isDiscounted;
    private float discountPercent;
    private double basePrice;
    private String currency= PACKAGE_DEFAULT_CURRENCY;
    private String brochurePath;
    private List<TourPackageTag> tagList;
    private boolean withFlight;
    private boolean isActivity;
    private List<PackageCategoryList> categoryList;

    @Override
    public int compareTo(TourPackageInfo tourPackageInfo) {
        return Double.compare(this.basePrice, tourPackageInfo.basePrice);
    }
}
