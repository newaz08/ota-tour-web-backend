package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageContentProjection;
import com.technonext.ota.b2c.tour.model.entity.TourPackage;

import com.technonext.ota.b2c.tour.model.entity.TourPackageContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourPackageContentRepository extends JpaRepository<TourPackageContent, Integer> {
    @Query(
            value = "SELECT tpc.ContentType, tpc.MimeType, tpc.Path " +
                    "FROM tour.TourPackageContent as tpc where tpc.TourPackageId=:tourPackageId",
            nativeQuery = true
    )
    List<TourPackageContentProjection> findTourPackageContent(Integer tourPackageId);


    @Query(value = "select max(ft.basePrice) as maxPrice,min(ft.basePrice) as minPrice from" +
            " (select (IIF(tp.CurrentMarkUp = 0.00, +" +
            " tp.NetPrice + (tp.NetPrice * ((select top 1 DefaultMarkup from tour.TourGeneralPolicy) / 100))," +
            " tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100))) As basePrice" +
            " from tour.TourPackage as tp  where tp.LocationId = :locationId) as ft",nativeQuery = true)
    PackagePriceLimitResponse getPackagePriceLimitByLocation(@Param("locationId") Integer locationId);
}
