package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackageCategoryResponse;
import com.technonext.ota.b2c.tour.model.entity.PackageCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackageCategoryRepository extends JpaRepository<PackageCategory,Short> {

    @Query(value = "SELECT pc.Id, pc.CategoryName, pc.IconPath FROM tour.PackageCategory as pc \n" +
            "where pc.IsApplicableForHajjUmrah=:isForHajjUmrah and pc.IsActive='true' and pc.IsDeleted='false'",
            nativeQuery = true)
    List<PackageCategoryResponse> findAllPackageCategory(@Param("isForHajjUmrah") boolean isForHajjUmrah);

    @Query(value = "SELECT pc.Id, pc.CategoryName, pc.IconPath FROM tour.PackageCategory as pc \n" +
            "where pc.IsApplicableForHajjUmrah='true' and pc.IsActive='true' and pc.IsDeleted='false'",
            nativeQuery = true)
    List<PackageCategoryResponse> findAllHajjUmrahPackageCategory();

}
