package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageContentResponse;

import com.technonext.ota.b2c.tour.model.entity.TourPackageContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourPackageContentRepository extends JpaRepository<TourPackageContent, Integer> {
    @Query(value = "SELECT tpc.ContentType, tpc.MimeType,concat(:baseUrl,tpc.Path) as Path \n" +
            "FROM tour.TourPackageContent as tpc where tpc.TourPackageId=:tourPackageId\n", nativeQuery = true)
    List<TourPackageContentResponse> findTourPackageContent(Integer tourPackageId, String baseUrl);

    @Query(value = "SELECT MAX(basePrice) AS max, MIN(basePrice) AS min\n" +
            "FROM (\n" +
            "         SELECT (\n" +
            "                    IIF(tp.CurrentMarkUp = 0.00, tp.NetPrice + (tp.NetPrice * (SELECT TOP 1 DefaultMarkup\n" +
            "                                         FROM tour.TourGeneralPolicy) / 100),\n" +
            "                        tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp / 100)))\n" +
            "                    ) AS basePrice\n" +
            "         FROM tour.TourPackage AS tp\n" +
            "         WHERE\n" +
            "             ((:locationId is null or tp.LocationId = :locationId)\n" +
            "                 and\n" +
            "              (:isNull = 1 or tp.Id IN (:packageIds)\n" +
            "                 )) and(tp.IsHajjUmrahPackage=:isForHajjUmrah)\n" +
            "     ) AS ft;", nativeQuery = true)
    PackagePriceLimitResponse getPackagePriceLimitByLocation(
            @Param("locationId") Integer locationId,
            @Param("packageIds") List<Integer> packageIds,
            @Param("isNull") Integer isNull, boolean isForHajjUmrah);
}
