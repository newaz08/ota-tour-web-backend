package com.technonext.ota.b2c.tour.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "isDeleted=false")
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
