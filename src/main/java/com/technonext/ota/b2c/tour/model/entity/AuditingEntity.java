package com.technonext.ota.b2c.tour.model.entity;

import com.technonext.ota.b2c.tour.util.SecurityUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class AuditingEntity {

    private Boolean isActive = true;
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private User createdBy;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "modifiedBy")
    private User modifiedBy;

    private LocalDateTime modifiedDate;

    @PrePersist
    private void onPersist() {
        createdDate = LocalDateTime.now();
        SecurityUtil.getCurrentUser().ifPresent(currentLoggedUser -> createdBy = currentLoggedUser);
    }

    @PreUpdate
    private void onModification() {
        modifiedDate = LocalDateTime.now();
        SecurityUtil.getCurrentUser().ifPresent(currentLoggedUser -> modifiedBy = currentLoggedUser);
    }
}
