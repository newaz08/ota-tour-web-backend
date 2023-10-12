package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.PackageCategoryResponse;
import com.technonext.ota.b2c.tour.repository.PackageCategoryRepository;
import com.technonext.ota.b2c.tour.service.iservice.PackageCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageCategoryServiceImpl implements PackageCategoryService {

   private final PackageCategoryRepository packageCategoryRepository;

    @Override
    public List<PackageCategoryResponse> getAllTourPackageCategory(boolean isForHajjUmrah) {
         return packageCategoryRepository.findAllPackageCategory(isForHajjUmrah);
    }

    @Override
    public List<PackageCategoryResponse> getAllHajjUmrahPackageCategory() {
        return packageCategoryRepository.findAllHajjUmrahPackageCategory();
    }
}
