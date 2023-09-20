package com.technonext.ota.b2c.tour.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IterneraryContents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItineraryContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer itineraryId;
    private Byte contentType;
    private String mimeType;
    private String path;
    private Byte position;
}
