package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.PackageCategoryResponse;
import java.util.List;

public interface PackageCategoryService {
        List<PackageCategoryResponse> getAllByInactiveHajjUmrahPackage();

        List<PackageCategoryResponse> getAllByActiveHajjUmrahPackage();
    }

