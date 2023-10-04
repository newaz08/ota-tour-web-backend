package com.technonext.ota.b2c.tour.model.entity;

import com.technonext.ota.b2c.tour.model.enums.PackageMode;
import com.technonext.ota.b2c.tour.model.enums.SuitableFor;
import com.technonext.ota.b2c.tour.model.enums.TourDirection;
import com.technonext.ota.b2c.tour.model.enums.TravelMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourPackage extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String packageName;
    private String packageCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationId")
    private Location location;

    @Enumerated(EnumType.STRING)
    private TourDirection tourDirection;

    @Enumerated(EnumType.STRING)
    private PackageMode packageMode;

    @Enumerated(EnumType.STRING)
    private SuitableFor suitableFor;

    private String cancellationText;

    @Enumerated(EnumType.STRING)
    private TravelMode travelMode;

    private Double activityDuration;
    private Short noOfNights;
    private Short noOfDays;
    private Short salesChannel;
    private Double netPrice;
    private Double currentMarkUp;
    private Boolean isGeneralDiscountApplicable;
    private Boolean isHajjUmrahPackage;
    private Double generalDiscountPercentage;
    private String packageOverview;
    private String inclusion;
    private String exclusion;
    private String brochurePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refundPolicyId")
    private RefundPolicy refundPolicy;

    private String otherPolicy;
    private Short adultMin;
    private Short adultMax;
    private Short childMin;
    private Short childMax;
    private Short infantMin;
    private Short infantMax;
    private String noOfPeopleForDisplay;
    private String disclaimer;
    private String travelTips;
    private LocalDate packageStartDate;
    private LocalDate packageEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourCountryId")
    private TourCountry country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourCityId")
    private TourCity city;
}