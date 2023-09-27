package com.technonext.ota.b2c.tour.mapper;

import com.technonext.ota.b2c.tour.dto.response.PackageCategoryResponse;
import com.technonext.ota.b2c.tour.model.entity.PackageCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackageCategoryMapper {
        List<PackageCategoryResponse> toPackageCategoryResponseList(List<PackageCategory> packageCategoryList);
}
