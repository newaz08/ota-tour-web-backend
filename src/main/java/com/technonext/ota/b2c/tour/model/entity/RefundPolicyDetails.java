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
public class RefundPolicyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "policyId")
    private RefundPolicy refundPolicy;

    @ManyToOne
    @JoinColumn(name = "policyTypeId")
    private RefundPolicyType refundPolicyType;

    private Integer refundPercentage;
    private Integer priorDays;
    private Integer refundAdjustmentDays;
    private Boolean isActive;
}
