package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.RefundPolicyResponse;
import com.technonext.ota.b2c.tour.model.entity.RefundPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RefundPolicyRepository extends JpaRepository<RefundPolicy,Long> {
    @Query(value = "select Description from tour.RefundPolicy",nativeQuery = true)
    List<RefundPolicyResponse> findRefundPolicyDescription();
}
