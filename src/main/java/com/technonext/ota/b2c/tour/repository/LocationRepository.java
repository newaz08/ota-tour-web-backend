package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.LocationProjection;
import com.technonext.ota.b2c.tour.model.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LocationRepository extends JpaRepository<Location, String> {
    @Query(value = """
            SELECT loc.Id,concat(loc.LocationName,',',tc.Name) locationName FROM tour.Location as loc
            left join tour.TourCountry as tc on tc.Id=loc.CountryId
            Where loc.LocationName LIKE '%'+:locationName+'%'""", nativeQuery = true)
    Page<LocationProjection> findByLocationName(String locationName, Pageable pageable);
}
