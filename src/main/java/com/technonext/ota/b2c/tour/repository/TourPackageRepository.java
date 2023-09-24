package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.PackagePriceLimitResponse;
import com.technonext.ota.b2c.tour.model.entity.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TourPackageRepository extends JpaRepository<TourPackage,Integer> {
    @Query(value = "select max(ft.calculated_price) as maxPrice,min(ft.calculated_price) as minPrice from" +
            " (select case when tp.CurrentMarkUp = 0 then tp.NetPrice +" +
            " (tp.NetPrice * (select top 1 tgp.DefaultMarkup from tour.TourGeneralPolicy as tgp))/100" +
            " else tp.NetPrice + (tp.NetPrice * tp.CurrentMarkUp)/100" +
            " end as calculated_price" +
            " from tour.TourPackage as tp where tp.LocationId = :locationId) as ft",nativeQuery = true)
    PackagePriceLimitResponse getPackagePriceLimitByLocation(Integer locationId);
}
