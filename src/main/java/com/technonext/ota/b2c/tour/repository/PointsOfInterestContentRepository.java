package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PointsOfInterestContentResponse;
import com.technonext.ota.b2c.tour.model.entity.PointsOfInterestContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointsOfInterestContentRepository extends JpaRepository<PointsOfInterestContent,Long> {

    @Query(value = "SELECT pic.ContentType, pic.MimeType,concat(:baseUrl,pic.Path) as Path,pic.Position " +
            "FROM tour.PointsOfInterestContent as pic where pic.PointsOfInterestId = :pointsOfInterestId",
            nativeQuery = true)
    List<PointsOfInterestContentResponse> findPointsOfInterestContentById(Long pointsOfInterestId,String baseUrl);
}
