package com.technonext.ota.b2c.tour.dto.response;

import com.technonext.ota.b2c.tour.model.entity.TourPackage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourPackageContentResponse {

    private Byte contentType;
    private String mimeType;
    private String path;
}
