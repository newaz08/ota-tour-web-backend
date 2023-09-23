package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackageTagResponse;
import com.technonext.ota.b2c.tour.model.entity.PackageTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageTagRepository extends JpaRepository<PackageTag,Short> {

    @Query(value = "select pt.tag_name as tagName,count(pt.id) as tagCount from package_tag as pt inner join\n" +
            "    tour_package_wise_tag_mapping as tptm on pt.id = tptm.package_tag_id \n" +
            "group by pt.id, pt.tag_name",nativeQuery = true)
    List<PackageTagResponse> findPackageTagWithCount();

}
