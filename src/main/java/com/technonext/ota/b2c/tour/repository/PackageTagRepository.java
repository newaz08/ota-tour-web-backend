package com.technonext.ota.b2c.tour.repository;

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
            " and tp.LocationId = :locationId" +
            " inner join tour.PackageTag pt on pt.id = tpwtm.PackageTagId" +
            " group by pt.id,pt.TagName",nativeQuery = true)
    List<PackageTagResponse> findPackageTagWithCountByLocationId(Integer locationId);

    @Query(value = "select pt.Id, pt.TagName from tour.TourPackage as tp \n " +
            "inner join tour.TourPackageWiseTagMapping as tpwtm on tp.id = tpwtm.TourPackageId\n" +
            "inner join tour.PackageTag as pt on pt.Id=tpwtm.PackageTagId\n" +
            "and tp.id=:tourPackageId and pt.IsDeleted=0 and pt.IsActive=1;",nativeQuery = true)

    List<TourPackageTagProjection> getPackageTagListPackageId(@Param("tourPackageId")Integer tourPackageId);



}
