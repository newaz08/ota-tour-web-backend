package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackageDescriptionResponse;
import com.technonext.ota.b2c.tour.model.entity.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TourPackageRepository extends JpaRepository<TourPackage,Integer> {

    @Query(value = "select tp.PackageName as packageName, tp.PackageEndDate as packageEndDate,\n" +
            "       tp.Disclaimer as disclaimer, tp.PackageOverview as packageOverview, tp.Inclusion as inclusion, \n" +
            "       tp.Exclusion as exclusion, tp.Traveltips as travelTips,location.LocationMapLink as locationMapLink \n" +
            "    from tour.TourPackage as tp inner join tour.Location as location\n" +
            "on tp.LocationId = location.Id and tp.Id = :tourPackageId",nativeQuery = true)
    PackageDescriptionResponse getTourPackageDetailsById(@Param("tourPackageId") Integer tourPackageId);
}
