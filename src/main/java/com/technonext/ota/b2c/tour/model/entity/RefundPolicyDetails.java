package com.technonext.ota.b2c.tour.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RefundPolicyDetails that)) {
            return false;
        }
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRefundPolicy(), that.getRefundPolicy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRefundPolicy());
    }
}
