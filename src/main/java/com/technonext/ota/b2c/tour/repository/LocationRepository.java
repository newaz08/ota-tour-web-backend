package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.model.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface LocationRepository extends JpaRepository<Location, String> {

    @Query(value = "SELECT * FROM tour.Location as loc \n" +
            "Where loc.LocationName LIKE %:locationName%", nativeQuery = true)
    Page<Location> findByLocationName(String locationName, Pageable pageable);
}
