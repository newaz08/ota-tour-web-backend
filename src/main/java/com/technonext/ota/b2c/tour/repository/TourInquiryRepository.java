package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.model.entity.TourInquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TourInquiryRepository extends JpaRepository<TourInquiry, Long> {
    Long countByDateOfInquiryAfter(LocalDateTime from);
}
