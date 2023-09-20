package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}
