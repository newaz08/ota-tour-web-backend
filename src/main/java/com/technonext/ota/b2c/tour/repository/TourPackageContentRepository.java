package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.TourPackageContentProjection;
import com.technonext.ota.b2c.tour.model.entity.TourPackage;

import com.technonext.ota.b2c.tour.model.entity.TourPackageContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourPackageContentRepository extends JpaRepository<TourPackageContent, Integer> {
    @Query(
            value = "SELECT tpc.ContentType, tpc.MimeType, tpc.Path " +
                    "FROM tour.TourPackageContent as tpc where tpc.TourPackageId=:tourPackageId",
            nativeQuery = true
    )
    List<TourPackageContentProjection> findTourPackageContent(Integer tourPackageId);
}
