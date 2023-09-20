package com.technonext.ota.b2c.tour.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LocationContents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer locationId;
    private Byte contentType;
    private String mimeType;
    private String path;
    private Byte position;
}
