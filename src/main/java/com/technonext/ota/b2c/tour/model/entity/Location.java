package com.technonext.ota.b2c.tour.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE tour.Location SET IsDELETED = 1 WHERE Id=? ")
@Where(clause = "IsDELETED=0")
public class Location extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String locationName;
    private String description;
    private String latitude;
    private String longitude;
    private String locationMapLink;
    private String weather;
    private String localCurrency;
    private String localLanguage;

    @JsonManagedReference
    @OneToMany(mappedBy = "location",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<PointsOfInterest> pointsOfInterests;

}
