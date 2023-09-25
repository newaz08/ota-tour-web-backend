package com.technonext.ota.b2c.tour.service.impl;

import com.technonext.ota.b2c.tour.dto.response.TourPackageContentProjection;
import com.technonext.ota.b2c.tour.dto.response.TourPackageContentResponse;
import com.technonext.ota.b2c.tour.mapper.TourPackageContentMapper;
import com.technonext.ota.b2c.tour.repository.TourPackageContentRepository;
import com.technonext.ota.b2c.tour.service.iservice.TourPackageContentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TourPackageContentServiceImpl implements TourPackageContentService {
    private final TourPackageContentRepository tourPackageContentRepository;
    private final TourPackageContentMapper tourPackageContentMapper;

    public TourPackageContentServiceImpl(TourPackageContentRepository tourPackageContentRepository, TourPackageContentMapper tourPackageContentMapper) {
        this.tourPackageContentRepository = tourPackageContentRepository;
        this.tourPackageContentMapper = tourPackageContentMapper;
    }

    @Override
    public List<TourPackageContentResponse> getAllTourPackageContent(Integer tourPackageId) {
        List<TourPackageContentProjection> tourPackageContent = tourPackageContentRepository.findTourPackageContent(tourPackageId);
        return tourPackageContentMapper.toTourPackageContentResponseList(tourPackageContent);
    }
}
