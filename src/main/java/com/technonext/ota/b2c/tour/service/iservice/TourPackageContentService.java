package com.technonext.ota.b2c.tour.service.iservice;

import com.technonext.ota.b2c.tour.dto.response.TourPackageContentResponse;
import com.technonext.ota.b2c.tour.model.entity.TourPackage;

import java.util.List;

public interface TourPackageContentService {
    List<TourPackageContentResponse> getAllTourPackageContent(Integer tourPackageId);
}
