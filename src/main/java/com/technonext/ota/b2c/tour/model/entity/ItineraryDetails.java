package com.technonext.ota.b2c.tour.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItineraryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "itineraryId")
    private TourPackageItinerary itinerary;

    @ManyToOne
    @JoinColumn(name = "itineraryElementId")
    private ItineraryElement itineraryElement;

    private Time startTime;
    private Time endTime;
}
