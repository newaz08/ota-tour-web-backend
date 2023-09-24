package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.model.entity.PackageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageTagRepository extends JpaRepository<PackageTag,Short> {

    @Query(value = "select pt.tag_name as tagName,count(pt.id) as tagCount from tour_package as tp" +
            " inner join tour_package_wise_tag_mapping as tpwtm on tp.id = tpwtm.tour_package_id" +
            " and tp.location_id = :locationId" +
            " inner join package_tag pt on pt.id = tpwtm.package_tag_id" +
            " group by pt.id,pt.tag_name",nativeQuery = true)
    List<PackageTagResponse> findPackageTagWithCountByLocationId(Integer locationId);

}
