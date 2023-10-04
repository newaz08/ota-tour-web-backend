package com.technonext.ota.b2c.tour.repository;

import com.technonext.ota.b2c.tour.model.entity.ChildPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChildPolicyRepository extends JpaRepository<ChildPolicy,Integer> {
    @Query(value = "select cp.PolicyDescription from tour.ChildPolicy cp \n" +
            "inner join tour.TourPackageWiseChildPolicyMapping TPWCPM on cp.Id = TPWCPM.ChildPolicyId \n" +
            "inner join tour.TourPackage tp on tp.Id = TPWCPM.TourPackageId and tp.Id=:tourPackageId \n" +
            "and cp.IsActive=1 and cp.IsDeleted=0",nativeQuery = true)
    List<String> findChildPolicyDescription(@Param("tourPackageId") Integer tourPackageId);
}
