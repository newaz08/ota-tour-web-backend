package com.technonext.ota.b2c.tour.model.entity;

import com.technonext.ota.b2c.tour.model.enums.InquiryChannel;
import com.technonext.ota.b2c.tour.model.enums.InquiryFor;
import com.technonext.ota.b2c.tour.model.enums.InquiryStatus;
import com.technonext.ota.b2c.tour.model.enums.InquiryType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TourInquiry extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private InquiryChannel inquiryChannel;
    @Enumerated(EnumType.STRING)
    private InquiryType inquiryType;
    @Enumerated(EnumType.STRING)
    private InquiryFor inquiryFor;
    private Date dateOfInquiry;
    @ManyToOne
    @JoinColumn(name = "tourPackageId")
    private TourPackage tourPackage;
    private String name;
    private String mobileNumber;
    private String email;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    private String inquiryLocation;
    private Date preferredJourneyData;
    private String customerLocation;
    private String requirement;
    @Enumerated(EnumType.STRING)
    private InquiryStatus inquiryStatus;
    private Date nextReminderDate;
    private Integer noOfTravellers;
    private Integer noOfAdults;
    private Integer noOfChilds;
    private Integer noOfInfants;
    private Long b2cUserId;
}