package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query(value = "SELECT * FROM tour.Location loc \n" +
            "Where LocationName LIKE '%:locationName%'", nativeQuery = true)
    List<Location> getLocations(@Param("locationName") String locationName);


}
