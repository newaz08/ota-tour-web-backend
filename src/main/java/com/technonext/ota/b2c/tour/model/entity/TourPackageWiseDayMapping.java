package com.technonext.ota.b2c.tour.model.entity;

import com.technonext.ota.b2c.tour.model.enums.TourDay;
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
public class TourPackageWiseDayMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tourPackageId")
    private TourPackage tourPackage;

    @Enumerated(EnumType.STRING)
    private TourDay tourDay;
}
