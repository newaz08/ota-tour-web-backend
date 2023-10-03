package com.technonext.ota.b2c.tour.model.entity;

import com.technonext.ota.b2c.tour.model.enums.ChildPolicyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "isDeleted=false")
public class ChildPolicy extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String policyTitle;
    private String policyDescription;

    @Enumerated(EnumType.STRING)
    private ChildPolicyType policyType;

    private Short maxAge;
    private Short feePercentage;
}
