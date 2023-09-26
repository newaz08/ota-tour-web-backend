package com.technonext.ota.b2c.tour.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageCategoryResponse {

    private Short id;
    private String categoryName;
    private String iconPath;
}
