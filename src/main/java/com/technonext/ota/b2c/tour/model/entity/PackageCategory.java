package com.technonext.ota.b2c.tour.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Table
@Where(clause = "isDeleted=false")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PackageCategory extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String categoryName;
    private String iconPath;
    private Boolean isApplicableForHajjUmrah;
}
