package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestContentProjection;
import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestProjection;
import com.technonext.ota.b2c.tour.model.entity.PointsOfInterestContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointsOfInterestContentRepository extends JpaRepository<PointsOfInterestContent,Long> {

    @Query(value = "SELECT pic.ContentType, pic.MimeType,concat(:baseUrl,pic.Path) as Path,pic.Position " +
            "FROM tour.PointsOfInterestContent as pic where pic.PointsOfInterestId = :pointsOfInterestId",
            nativeQuery = true)
    List<PointsOfInterestContentProjection> findPointsOfInterestContentById(Long pointsOfInterestId, String baseUrl);

    @Query(value = "select poi.Id,poi.POIName poiName, poi.Description description, concat(:baseUrl,poic.Path) contentPath,\n" +
            "poic.ContentType contentType, poic.MimeType mimeType from tour.PointsOfInterest poi \n" +
            "left join (select PointsOfInterestId, Path,ContentType, mimeType from tour.PointsOfInterestContent \n" +
            "where Position=2) poic on poi.Id = poic.PointsOfInterestId \n" +
            "and poi.LocationId=:locationId ", nativeQuery = true)
    List<PointsOfInterestProjection> getPackagePointOfInterest(
            @Param("locationId") Integer locationId, String baseUrl);
}
