package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackageDetailsProjection;
import com.technonext.ota.b2c.tour.model.entity.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TourPackageRepository extends JpaRepository<TourPackage, Integer> {
    @Query(value = "DECLARE @json nvarchar(max);\n" +
            ";WITH src (n) AS\n" +
            "(SELECT\n" +
            "    tp.id AS id,\n" +
            "    tp.PackageName AS title,\n" +
            "    tc.Name AS country,\n" +
            "    tct.CityName AS city,\n" +
            "    tp.SuitableFor AS suitableFor,\n" +
            "    tp.CancellationText AS cancellationText,\n" +
            "    tp.NoOfPeopleForDisplay AS noOfPeopleForDisplay,\n" +
            "    format(tp.PackageStartDate,'yyyy-MM') As packageStart,\n" +
            "    format(tp.PackageEndDate, 'yyyy-MM') As packageEnd,\n"+
            "    IIF(tp.PackageMode = 'ACTIVITY_BASED', tp.ActivityDuration, tp.NoOfDays) AS duration,\n" +
            "    IIF(tp.PackageMode = 'ACTIVITY_BASED',concat(tp.ActivityDuration,' hour(s)')," +
            "           CONCAT(tp.NoOfNights, ' Nights ', tp.NoOfDays, ' Days')) AS durationText,\n" +
            "    concat(:baseUrl,'/',tpackc.Path) AS featuredPhoto,\n" +
            "    tp.IsGeneralDiscountApplicable           AS discounted,\n" +
            "    tp.GeneralDiscountPercentage                                AS discountPercent,\n" +
            "    IIF(tp.CurrentMarkUp = 0.00,\n" +
            "         tp.NetPrice + (tp.NetPrice * ((SELECT TOP 1 DefaultMarkup FROM tour.TourGeneralPolicy) / 100)),\n" +
            "         tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100)) AS basePrice,\n" +
            "    concat(:baseUrl,'/',tp.BrochurePath)                                     AS brochurePath,\n" +
            "    (\n" +
            "        SELECT\n" +
            "            pt.id,\n" +
            "            pt.TagName AS name,\n" +
            "            pt.ColorCode AS colorCode\n" +
            "        FROM tour.PackageTag pt\n" +
            "                 INNER JOIN tour.TourPackageWiseTagMapping tpwm ON pt.id = tpwm.PackageTagId\n" +
            "        WHERE tpwm.TourPackageId = tp.id\n" +
            "        FOR JSON AUTO \n" +
            "    ) AS tagList,\n" +
            "    IIF(tp.TravelMode = 'WITH_FLIGHT', 1, 0) AS withFlight,\n" +
            "    IIF(tp.PackageMode = 'ACTIVITY_BASED', 1, 0) AS activity,\n" +
            "    (\n" +
            "        SELECT tpwcm.id\n" +
            "        FROM tour.TourPackageWiseCategoryMapping tpwcm\n" +
            "        WHERE tpwcm.TourPackageId = tp.id\n" +
            "        FOR JSON AUTO \n" +
            "    ) AS categoryList\n" +
            "FROM tour.TourPackage tp\n" +
            "         INNER JOIN tour.Location loc ON tp.LocationId = loc.id and tp.LocationId=:locationId \n" +
            "and tp.IsActive = 1\n" +
            "         LEFT JOIN tour.TourCountry tc ON tp.TourCountryId = tc.id\n" +
            "         LEFT JOIN tour.TourCity tct ON tc.Id = tct.TourCountryId\n" +
            "         LEFT JOIN (SELECT TOP 1 Id, Path FROM tour.TourPackageContent WHERE IsFeatured = 1) tpackc " +
            "ON tp.Id = tpackc.Id where tp.IsDeleted=0 for json path )\n" +
            "SELECT @json = src.n\n" +
            "FROM src\n" +
            "SELECT @json, LEN(@json);\n",nativeQuery = true)
    String getLocationWiseTourPackages(
            @Param("locationId") Integer locationId, @Param("baseUrl") String baseUrl);

    @Query(value = "select tp.PackageName as packageName,FORMAT(tp.PackageEndDate,'dd MMM yyyy') as packageEndDate," +
            "concat(tp.NoOfDays,' Days') as noOfDays, \n" +
            "concat(tp.NoOfNights,' Nights') as noOfNights,(IIF(tp.CurrentMarkUp = 0.00, \n" +
            "tp.NetPrice + (tp.NetPrice * ((SELECT TOP 1 DefaultMarkup FROM tour.TourGeneralPolicy) / 100)), \n" +
            "tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100))) as basePrice,tp.SuitableFor as suitableFor, " +
            "tp.CancellationText as cancellationText,tp.NoOfPeopleForDisplay as noOfPeopleForDisplay," +
            "tp.GeneralDiscountPercentage as discountPercent," +
            "tp.Disclaimer as disclaimer, tp.PackageOverview as packageOverview, tp.Inclusion as inclusion, \n" +
            "tp.Exclusion as exclusion, tp.Traveltips as travelTips,location.LocationMapLink as locationMapLink \n" +
            "from tour.TourPackage as tp inner join tour.Location as location\n" +
            "on tp.LocationId = location.Id and tp.Id = :tourPackageId",nativeQuery = true)
    PackageDetailsProjection getTourPackageDetailsById(@Param("tourPackageId") Integer tourPackageId);

    @Query(value = "select tp.OtherPolicy from tour.TourPackage tp where tp.Id=:tourPackageId", nativeQuery = true)
    String getPackageOtherPolicy(@Param("tourPackageId") Integer tourPackageId);

}
