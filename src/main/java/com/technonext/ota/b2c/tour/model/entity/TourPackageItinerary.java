package com.technonext.ota.b2c.tour.model.entity;

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
public class TourPackageItinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer tourPackageId;
    private Short itineraryDay;
    private String itineraryTitle;
    private String description;
    private Short itineraryElementId;
    private String startTime;
    private String endTime;
    private Short sortOrder;
}
