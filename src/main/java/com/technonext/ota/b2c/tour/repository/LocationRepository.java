package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LocationRepository extends JpaRepository<Location, String> {

    @Query(value = "SELECT * FROM tour.Location as loc \n" +
            "Where loc.LocationName LIKE %:locationName%", nativeQuery = true)
    List<Location> findByLocation(String locationName);
}
