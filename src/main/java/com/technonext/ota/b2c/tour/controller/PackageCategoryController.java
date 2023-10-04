package com.technonext.ota.b2c.tour.controller;

import com.technonext.ota.b2c.tour.dto.response.PackageCategoryResponse;
import com.technonext.ota.b2c.tour.service.iservice.PackageCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.technonext.ota.b2c.tour.constant.APIEndpointConstants.PACKAGE_CATEGORY_ENDPOINT;

@RestController
@RequestMapping(PACKAGE_CATEGORY_ENDPOINT)
@RequiredArgsConstructor
@Tag(name = "Package Category Controller", description = "API Endpoints for Package Category related operations.")
public class PackageCategoryController {

    private final PackageCategoryService packageCategoryService;

    @GetMapping("/search")
    @Operation(summary = "API Endpoints for Package Category searching")
    public ResponseEntity<List<PackageCategoryResponse>> search() {
        return new ResponseEntity<>(packageCategoryService.getAllByInactiveHajjUmrah(), HttpStatus.OK);
    }
}
