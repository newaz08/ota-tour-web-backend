package com.technonext.ota.b2c.tour.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourGeneralPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double defaultMarkUp;
    @ManyToOne
    @JoinColumn(name = "createdBy")
    private User createdBy;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "modifiedBy")
    private User modifiedBy;

    private LocalDateTime modifiedDate;
}
