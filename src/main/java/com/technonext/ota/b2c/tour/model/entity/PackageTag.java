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
public class PackageTag extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tagName;
    private String tagDescription;
    private Boolean isNextTripTag;
    private Boolean isApplicableForHajjUmrah;
    private String colorCode;
}
