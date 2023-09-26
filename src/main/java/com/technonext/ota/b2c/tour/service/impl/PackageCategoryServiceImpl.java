package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.PackageCategoryResponse;
import com.technonext.ota.b2c.tour.mapper.PackageCategoryMapper;
import com.technonext.ota.b2c.tour.model.entity.PackageCategory;
import com.technonext.ota.b2c.tour.repository.PackageCategoryRepository;
import com.technonext.ota.b2c.tour.service.iservice.PackageCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageCategoryServiceImpl implements PackageCategoryService {

   private final PackageCategoryRepository packageCategoryRepository;
   private final PackageCategoryMapper packageCategoryMapper;

    public PackageCategoryServiceImpl(PackageCategoryRepository packageCategoryRepository, PackageCategoryMapper packageCategoryMapper) {
        this.packageCategoryRepository = packageCategoryRepository;
        this.packageCategoryMapper = packageCategoryMapper;
    }

    @Override
    public List<PackageCategoryResponse> getAllByInactiveHajjUmrah() {
        List<PackageCategory> packageCategories=packageCategoryRepository.findAllByInactiveHajjUmrah();
        return packageCategoryMapper.toPackageCategoryResponseList(packageCategories);
    }
}
