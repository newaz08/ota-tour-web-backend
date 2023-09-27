package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.model.entity.PackageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageTagRepository extends JpaRepository<PackageTag,Short> {

    @Query(value = "select pt.TagName as tagName,count(pt.id) as tagCount from tour.TourPackage as tp" +
            " inner join tour.TourPackageWiseTagMapping as tpwtm on tp.id = tpwtm.TourPackageId" +
            " and tp.LocationId = :locationId" +
            " inner join tour.PackageTag pt on pt.id = tpwtm.PackageTagId" +
            " group by pt.id,pt.TagName",nativeQuery = true)
    List<PackageTagResponse> findPackageTagWithCountByLocationId(Integer locationId);
}
