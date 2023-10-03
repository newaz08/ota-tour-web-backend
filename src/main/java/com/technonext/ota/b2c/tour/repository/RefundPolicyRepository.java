package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.RefundPolicyResponse;
import com.technonext.ota.b2c.tour.model.entity.RefundPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RefundPolicyRepository extends JpaRepository<RefundPolicy,Long> {
    @Query(value = "select Description from tour.RefundPolicy rp \n" +
            "inner join tour.TourPackage tp  on rp.Id = tp.RefundPolicyId \n" +
            "and tp.Id=:tourPackageId and rp.IsDeleted=0 and rp.IsActive=1",nativeQuery = true)
    List<String> findRefundPolicyDescription(@Param("tourPackageId") Integer tourPackageId);
}
