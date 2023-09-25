package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.model.entity.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TourPackageRepository extends JpaRepository<TourPackage,Integer> {
    @Query(value = "select max(ft.basePrice) as maxPrice,min(ft.basePrice) as minPrice from" +
            " (select (IIF(tp.CurrentMarkUp = 0.00, +" +
            " tp.NetPrice + (tp.NetPrice * ((select top 1 DefaultMarkup from tour.TourGeneralPolicy) / 100))," +
            " tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100))) As basePrice" +
            " from tour.TourPackage as tp  where tp.LocationId = :locationId) as ft",nativeQuery = true)
    PackagePriceLimitResponse getPackagePriceLimitByLocation(@Param("locationId") Integer locationId);
}
