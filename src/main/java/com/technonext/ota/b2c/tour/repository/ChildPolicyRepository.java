package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.dto.response.ChildPolicyResponse;
import com.technonext.ota.b2c.tour.model.entity.ChildPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChildPolicyRepository extends JpaRepository<ChildPolicy,Integer> {
    @Query(value = "select PolicyDescription from tour.ChildPolicy",nativeQuery = true)
    List<ChildPolicyResponse> findChildPolicyDescription();
}
