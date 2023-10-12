package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.NextTripTagWithPackageInfoProjection;
import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.dto.response.TourPackageTagProjection;
import com.technonext.ota.b2c.tour.model.entity.PackageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageTagRepository extends JpaRepository<PackageTag,Short> {

    @Query(value = "select pt.Id, pt.TagName as name,count(pt.id) as count from tour.TourPackage as tp" +
            " inner join tour.TourPackageWiseTagMapping as tpwtm on tp.id = tpwtm.TourPackageId" +
            " and(:locationId is null or tp.LocationId = :locationId) \n" +
            " and (:isNull=1 or tp.Id in(:packageIds))\n" +
            " and(tp.IsHajjUmrahPackage=:isForHajjUmrah)\n"+
            " inner join tour.PackageTag pt on pt.id = tpwtm.PackageTagId" +
            " group by pt.id,pt.TagName",nativeQuery = true)
    List<PackageTagResponse> findPackageTagWithCountByLocationId(
            @Param("locationId") Integer locationId,
            @Param("packageIds") List<Integer> packageIds,
            @Param("isNull") Integer isNull,
            @Param("isForHajjUmrah") boolean isForHajjUmrah);

    @Query(value = "select pt.Id, pt.TagName from tour.TourPackage as tp \n " +
            "inner join tour.TourPackageWiseTagMapping as tpwtm on tp.id = tpwtm.TourPackageId\n" +
            "inner join tour.PackageTag as pt on pt.Id=tpwtm.PackageTagId\n" +
            "and tp.id=:tourPackageId and pt.IsDeleted=0 and pt.IsActive=1;",nativeQuery = true)

    List<TourPackageTagProjection> getPackageTagListByPackageId(@Param("tourPackageId")Integer tourPackageId);



    @Query(value = "\n" +
            "WITH BasePriceCTE AS (\n" +
            "    SELECT\n" +
            "        tp.TourCountryId AS countryId,\n" +
            "        MIN(\n" +
            "                IIF(\n" +
            "                            tp.CurrentMarkUp = 0.00,\n" +
            "                            tp.NetPrice + (tp.NetPrice * (DefaultMarkup / 100)),\n" +
            "                            tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp / 100))\n" +
            "                    )\n" +
            "            ) AS BasePrice\n" +
            "    FROM\n" +
            "        tour.TourPackage AS tp\n" +
            "            INNER JOIN\n" +
            "        tour.TourCountry AS tc ON tc.Id = tp.TourCountryId\n" +
            "            CROSS JOIN\n" +
            "        tour.TourGeneralPolicy\n" +
            "    WHERE\n" +
            "            tp.Id IN (\n" +
            "            SELECT DISTINCT TPWTM.TourPackageId\n" +
            "            FROM tour.TourPackageWiseTagMapping AS TPWTM\n" +
            "                     INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId\n" +
            "            and pt.IsNextTripTag = 1 and pt.Id in(:nextTripTagIds)\n" +
            "        )\n" +
            "    GROUP BY\n" +
            "        tp.TourCountryId) \n" +
            "SELECT\n" +
            "    tc.Name AS countryName,\n" +
            "    bp.countryId AS countryId,\n" +
            "    bp.BasePrice,\n" +
            "    (\n" +
            "        SELECT\n" +
            "            distinct pt.id as tagId\n" +
            "        FROM\n" +
            "            tour.TourPackageWiseTagMapping AS TPWTM\n" +
            "                INNER JOIN\n" +
            "            tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId\n" +
            "        and pt.Id in(:nextTripTagIds) and\n" +
            "                TPWTM.TourPackageId IN (\n" +
            "                SELECT DISTINCT TPWTM.TourPackageId\n" +
            "                FROM tour.TourPackageWiseTagMapping AS TPWTM\n" +
            "                         INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId\n" +
            "                and pt.IsNextTripTag = 1 and pt.Id in(:nextTripTagIds)\n" +
            "            )\n" +
            "          AND pt.IsNextTripTag = 1\n" +
            "        FOR json auto\n" +
            "    ) AS TagList\n" +
            "FROM\n" +
            "    tour.TourCountry AS tc\n" +
            "        INNER JOIN\n" +
            "    BasePriceCTE AS bp ON tc.Id = bp.countryId;",nativeQuery = true)
    List<NextTripTagWithPackageInfoProjection> getNextTripTagWithPackageInfoGroupByCountry(
            @Param("nextTripTagIds") List<Integer> nextTripTagIds
    );


    @Query(value = "select top 5 Id, TagName name from tour.PackageTag;", nativeQuery = true)
    List<PackageTagResponse> getTopFiveNextTripTag();


}
