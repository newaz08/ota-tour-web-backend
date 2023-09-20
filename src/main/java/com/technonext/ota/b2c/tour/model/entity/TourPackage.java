package com.technonext.ota.b2c.tour.model.entity;

import com.technonext.ota.b2c.tour.model.enums.SuitableFor;
import com.technonext.ota.b2c.tour.model.enums.TourDirection;
import com.technonext.ota.b2c.tour.model.enums.TravelMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String packageName;
    private String packageCode;
    private Integer locationId;

    @Enumerated(EnumType.STRING)
    private TourDirection tourDirection;

    private String packageMode;

    @Enumerated(EnumType.STRING)
    private SuitableFor suitableFor;

    @Enumerated(EnumType.STRING)
    private TravelMode travelMode;

    private Double activityDuration;
    private Short noOfNights;
    private Short noOfDays;
    private Short salesChannel;
    private Double basePrice;
    private String packageOverview;
    private String packageBookingSteps;
    private String facilityAtOnce;
    private String additionalInformation;
    private String inclusion;
    private String exclusion;
    private String brochurePath;
    private Short refundPolicyId;
    private String otherPolicy;
    private Short adultMin;
    private Short adultMax;
    private Short childMin;
    private Short childMax;
    private Short infantMin;
    private Short infantMax;
    private String travelTips;
}
