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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Where(clause = "isDeleted = false")
@SQLInsert(sql = "UPDATE tour.tourInquiry set IsActive = 1 and IsDeleted = 0 where Id=? ")
@SQLDelete(sql = "UPDATE tour.tourInquiry SET IsDELETED = 1 WHERE Id=? ")
public class TourInquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private InquiryChannel inquiryChannel;
    @Enumerated(EnumType.STRING)
    private InquiryType inquiryType;
    @Enumerated(EnumType.STRING)
    private InquiryFor inquiryFor;
    private LocalDateTime dateOfInquiry;
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
    private LocalDateTime preferredJourneyDate;
    private String customerLocation;
    private String requirement;
    @Enumerated(EnumType.STRING)
    private InquiryStatus inquiryStatus;
    private LocalDateTime nextReminderDate;
    private Integer noOfTravellers;
    private Integer noOfAdults;
    private Integer noOfChilds;
    private Integer noOfInfants;
    private String inquiryId;
    private Boolean isActive = true;
    private Boolean isDeleted = false;
    private Long b2cUserId;
}
